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
            }
        },
        methods : {
        	redirect: (url) => {
        		window.location.href = url
        	},
            activeSubmitForm: ($rooter) => {
                
            },
            extractFormParams: ($formRoot) => {
                params_json = {}
                if (!$formRoot) {
                    return params_json
                }
                
                $params = $formRoot.serializeArray()
                $.each($params, (index, value) => {
                    params_json[value['name']] = value['value']
                })
                
                return params_json
            },
            submitSignin: () => {
                try {
                    $form = $('.signin-form')
                    params_json = auth.methods.extractFormParams($form)
                    params_json['account'] = params_json['username']
                    $.ajax({
                        url: auth.data.urls.signin.form,
                        method: 'POST',
                        data: params_json,
                        timeout: 1000,
                        beforeSend: (xhr) => {
                            xhr.setRequestHeader('Authorization', 'Basic anRvZG86MTEx')
                        },
                        success: (resp) => {
                            if (typeof(resp) == 'string') {
                                resp = JSON.parse(resp)
                            }
                            if (resp['access_token']) {
                            	console.log('Authentication success： wandan.site')
                            	auth.methods.redirect(auth.data.urls.root)
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
                    conole.log(err)
                }
                finally {
                    return false
                }
            }
        }
    }

    $(document).ready(function() {
        $('.signin-form').submit(auth.methods.submitSignin)
    })
})()