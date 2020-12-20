## Image for dev
FROM node:latest as builder
WORKDIR /app
COPY package.json ./
RUN npm install
RUN npm install -g @angular/cli
COPY . /app

CMD  ng serve --proxy-config proxy.conf.json --host 0.0.0.0
