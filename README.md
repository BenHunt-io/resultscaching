# Build
mvn clean package && docker build -t com.benhunt/resultscaching .

# RUN

docker rm -f resultscaching || true && docker run -d -p 8080:8080 -p 4848:4848 --name resultscaching com.benhunt/resultscaching 