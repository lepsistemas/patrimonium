*Up mongodb instance*

docker run -d -p 27017:27017 -v ~/data/mongodb:/data/db mongo

*Run dev mode*

-Dspring.profiles.active=dev -Djwt.secret=$SECRET_KEY -Duser.super.password=super -Dmail.host=$MAIL_HOST -Dmail.username=$MAIL_USER -Dmail.password=$MAIL_PASS

*Run prod mode*

-Dspring.profiles.active=prod -Djwt.secret=$SECRET_KEY -Duser.super.password=$SUPER_PASSWORD -Dspring.data.mongodb.uri=$MONGO_URI -Dmail.host=$MAIL_HOST -Dmail.username=$MAIL_USER -Dmail.password=$MAIL_PASS