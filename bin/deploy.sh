#!/bin/bash

user=`whoami`

if [ $user = "jesus" ]

then
    cd /home/jesus/developkit/repository/git/passport
    
    # update the code from git
    git pull -r

    if [ $? -eq 0 ]

    then
        sh /home/jesus/developkit/service/tomcat/passport/t1/bin/shutdown.sh
        
        rm -rf /home/jesus/developkit/service/tomcat/passport/t1/webapps/*

        cp /home/jesus/developkit/repository/git/passport/passport/target/passport*.war /home/jesus/developkit/service/tomcat/passport/t1/webapps/passport.war

        if [ $? -eq 0 ]       
        then
            sh /home/jesus/developkit/service/tomcat/passport/t1/bin/startup.sh
        else
            echo "cp war file failed"
        fi
    else
        echo "Git pull -r failed"
    fi
else
    echo "Please execute as jesus"
fi