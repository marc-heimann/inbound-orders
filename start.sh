#!/bin/bash
/usr/bin/nohup /usr/bin/java -jar /home/pi/microprofile-demo/payara-micro-5.181.jar --disablephonehome --nocluster --nohostaware --logToFile /home/pi/microprofile-demo/membership.log /home/pi/microprofile-demo/membership.war > /dev/null &d