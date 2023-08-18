FROM node:20 as build

ENV NODEOPTIONS --max-old-space-size=4096
ENV GENERATE_SOURCEMAP false

WORKDIR /dist/src/app
RUN npm cache clean --force
COPY . .
RUN npm install
RUN npm run build

FROM nginx:latest AS ngi
COPY --from=build /dist/src/app/dist/frontend /usr/share/nginx/html
COPY /nginx.conf  /etc/nginx/conf.d/default.conf
EXPOSE 8080
