## Stage 1 - Lets build the "deployable package"
FROM node:latest as frontend-build
WORKDIR /fullstack/frontend

# Step 1 - Download all package dependencies first.
# We will redownload dependencies only when packages change.
COPY package.json package-lock.json ./
RUN npm install

# Step 2 - Copy all source and run build
COPY . ./
RUN npm run build

## Stage 2 - Let's build a minimal image with the "deployable package"
FROM nginx:stable
COPY --from=frontend-build /fullstack/frontend/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]