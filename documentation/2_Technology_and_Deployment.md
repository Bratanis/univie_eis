---
title: Technology
type: description
---
# Technology and Deployment

The following technological environment is used for the realisation:

- *Microservice configuration and runtime:* OLIVE MSC
- *Implementation of service:* Java, JDK 17, MySQL, Docker, Springboot
- *UI/Frontend:* HTML and CanvasJS


The deployment is handled as a pipeline in the Git environment. Every push/commit to the master branch results in:

1. Build of software
2. Creation of a docker image
3. Deployment of the docker image on the eis.dke.univie.ac.at server

The deployment configuration itself is specified in .gitlab-ci.yml 