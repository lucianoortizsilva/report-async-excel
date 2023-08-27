# get the base node image
FROM node:current-alpine3.18@sha256:254989045b0555ee411cd8fe7bd8e3ae306fef34b4c9d5dfd020bdda86cdad97

# set the working dir for container
WORKDIR /app

# copy the json file first
COPY ./package.json /app

# install npm dependencies
RUN npm install

# copy other project files
COPY . .

# build the folder
CMD [ "npm", "run", "start" ]
