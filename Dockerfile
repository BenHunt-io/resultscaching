FROM airhacks/glassfish
COPY ./target/resultscaching.war ${DEPLOYMENT_DIR}
