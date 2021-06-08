FROM adoptopenjdk:16-jre
COPY Server/target/modules app/modules
COPY Database/target/classes /app/database
COPY Engine/target/classes /app/engine
COPY Server/target/classes /app/server
COPY ServiceProviderInterface/target/classes /app/serviceprovider
COPY PoemResponse/target/classes /app/poemResponse
COPY Server/target/web /app/web


ENTRYPOINT [ "java", "--module-path", "/app/database:/app/engine:/app/server:/app/serviceprovider:/app/poemResponse:/app/modules", "-m" ,"Server/server.Main" ]