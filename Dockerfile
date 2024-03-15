FROM node:17-alpine
COPY frontend/htx .
RUN npm install
EXPOSE 3000
CMD ["npm", "start"]