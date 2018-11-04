(function() {

    const auth = {
        data : {
            urls: {
            	root: 'http://passport.wandan.site',
                signin: {
                    form: '/user/signin',
                    oauth: {
                        github: {
                            
                        },
                        wechat: {
                            
                        }
                    }
                }
            },
        },
        rules: {
            'si-ac': [/^[\S]+$/, 'Invalid account', 'Invalid account'],
            'si-pa': [/^[\S]+$/, 'Invalid password', 'Invalid password'],
        },
        methods : {
        	redirect: (url) => {
        		window.location.href = url;
        	},
        	errMsg: (msg) => {
                let $errMsg = $('#err-msg');
                if ($errMsg && $errMsg.length) {
                    $errMsg.find('.text').text(msg).attr('data-error', 'true');
                }
        	},
            activeSubmitForm: ($formRoot) => {
                
            },
            extractFormParams: ($formRoot) => {
                let params_json = {};
                if (!$formRoot) {
                    return params_json;
                }
                
                $params = $formRoot.serializeArray();
                $.each($params, (index, value) => {
                    params_json[value['name']] = value['value'];
                })
                
                return params_json;
            },
            validateForm: ($formRoot) => {
                let result = {
                    'success': true,
                    'errMsg': ''
                };
                if (!$formRoot) {
                    result['success'] = false;
                    result['errMsg'] = 'Invalid form rooter';
                    return result;
                }

                $formRoot.find('input[data-verify]').each(function () {
                    let $input = $(this);
                    let regex = $(this).attr('data-verify');
                    let value = $(this).val();
                    let validator = auth.rules[regex];

                    if (!validator) { return true; }

                    // ~ Validate build-in rules
                    let buildInRules = $input.attr('data-rules');
                    if (buildInRules) {
                        let rules = buildInRules.split(':');
                        for (let i = 0; i < rules.length; i++) {
                            const rule = rules[i];
                            switch (rule) {
                                case 'required':
                                    if (typeof (value) === 'undefined' || !value) {
                                        let errMsg = '';
                                        try {
                                            errMsg = validator[2];
                                        } catch (e) {
                                            errMsg = 'Required field cannot be empty';
                                        }

                                        $input.addClass('form-error');
                                        $input.focus(function() {
                                            $input.removeClass('form-error');
                                            auth.methods.errMsg('');
                                        });

                                        result = {
                                            'success': false,
                                            'errMsg': errMsg
                                        };
                                    }
                                    break;
                                default:
                                    break;
                            }

                            if (!result['success']) {
                                return false;
                            }
                        }
                    }

                    // ~ Validate custom rules
                    if (typeof (value) != 'undefined' && value != null && value.match(validator[0])) {
                        // Passed verify
                        return true;
                    }
                    else {
                        result = {
                            'success': false,
                            'errMsg': validator[1]
                        };

                        $input.addClass('form-error');
                        $input.focus(function() {
                            $input.removeClass('form-error');
                            auth.methods.errMsg('');
                        })

                        return false;
                    }
                });

                // ~ Return
                return result;
            },
            renderRememberMeWidget: () => {

                $('.remember-me .checkbox').click(function () {
                    let checked = $(this).is(':checked');
                    if (checked) {
                        $('.remember-me .widget').removeClass('icon-square');
                        $('.remember-me .widget').addClass('icon-squarecheck');
                    }
                    else {
                        $('.remember-me .widget').removeClass('icon-squarecheck');
                        $('.remember-me .widget').addClass('icon-square');
                    }
                });
            },
            submitSignin: () => {
                try {
                    // ~ Extract parameter
                    let $form = $('.signin-form');
                    params_json = auth.methods.extractFormParams($form);
                    params_json['account'] = params_json['username'];

                    // ~ Validate parameter
                    let result = auth.methods.validateForm($form);
                    if (!result['success']) {
                        auth.methods.errMsg(result['errMsg']);
                        return false;
                    }

                    // ~ Execute signin request
                    $.ajax({
                        url: auth.data.urls.signin.form,
                        method: 'POST',
                        data: params_json,
                        timeout: 1000,
                        beforeSend: (xhr) => {
                            xhr.setRequestHeader('Authorization', 'Basic anRvZG86MTEx');
                        },
                        success: (resp) => {
                            if (typeof(resp) == 'string') {
                                resp = JSON.parse(resp);
                            }
                            if (resp['access_token']) {
                            	console.log('Authentication success： wandan.site');
                            	auth.methods.redirect(auth.data.urls.root);
                            }
                        },
                        error: (xhr, msg, err) => {
                            console.log(xhr)
                            console.log(msg)
                            console.log(err)
                        }
                    })
                }
                catch (err) {
                    console.log(err);
                }
                finally {
                    return false;
                }
            }
        }
    }

    $(document).ready(function() {
        auth.methods.renderRememberMeWidget()
        $('.signin-form').submit(auth.methods.submitSignin)
    })
})()