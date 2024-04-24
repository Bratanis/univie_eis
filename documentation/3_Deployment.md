---
title: Deployment
type: description
---
The deployment is handled as a pipeline in the Git environment. Every push/commit to the master branch results in:

1. Build of software
2. Creation of a docker image
3. Deployment of the docker image on the eis.dke.univie.ac.at server

The docker container exposes an internal port that is accessible via an nginx proxy configuration.