# CMPE 172 - Lab #8 Kong API Gateway Notes

- In this Lab, you will will be deploying Kong API Gateway to Google GKE and configuring the Gateway to route to your Starbucks REST API from Lab #6. In addition, you will add API Key Authentication to your API and test your API via Postman.

## The /labs/lab8 folder includes:
- images (screenshots) 
- README.md (lab notes)

## Install Kong on Local Docker 
- [Install Kong](https://konghq.com/install)
- [Docker Hub Kong](https://hub.docker.com/_/kong)

## Create Kong Docker Network
- Professor Nguyen provided the commands below:

```sh
docker network create --driver bridge kong-network
```

```sh
docker network inspect kong-network
```

![docker network](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/docker-network.png)

## Run Starbucks API in Docker
- Note: use your own Starbucks API Image

```sh
docker run -d --name starbucks-api --network kong-network -td spring-starbucks-api
```

![run starbucks api](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/run-starbucks-api.png)

## Run Kong Docker in DB-Less Mode

```sh
docker run -d --name kong \
--network=kong-network \
-e "KONG_DATABASE=off" \
-e "KONG_PROXY_ACCESS_LOG=/dev/stdout" \
-e "KONG_ADMIN_ACCESS_LOG=/dev/stdout" \
-e "KONG_PROXY_ERROR_LOG=/dev/stderr" \
-e "KONG_ADMIN_ERROR_LOG=/dev/stderr" \
-e "KONG_ADMIN_LISTEN=0.0.0.0:8001, 0.0.0.0:8444 ssl" \
-p 80:8000 \
-p 443:8443 \
-p 8001:8001 \
-p 8444:8444 \
kong:2.4.0
    
docker exec -it kong kong config init /home/kong/kong.yaml
docker exec -it kong cat /home/kong/kong.yaml >> kong.yaml

===== kong.yaml =====

_format_version: "1.1"

services:
- name: starbucks
  protocol: http
  host: starbucks-api
  port: 8080
  path: /
  plugins:
  - name: key-auth  
  routes:
  - name: api
    paths:
    - /api
    
consumers:
- username: apiclient
  keyauth_credentials:
  - key: 2H3fONTa8ugl1IcVS7CjLPnPIS2Hp9dJ

===== kong.yaml =====

http :8001/config config=@kong.yaml
docker exec -it kong kong reload

http localhost/api/ping
http localhost/api/ping apikey:2H3fONTa8ugl1IcVS7CjLPnPIS2Hp9dJ
```

![make kong dbless](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/make-kong-dbless.png)

![http one](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/http-one.png)

![http two](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/http-two.png)

![http three](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/http-three.png)

![reload ping](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/reload-ping.png)

## Deploy Kong on Google GKE 
**Build and Push Your Starbucks API Docker Image to Docker Hub**

- I logged into my Docker account after running this command:

```sh
docker login
```

- It will prompt you to enter your username and password.

```sh
docker build -t cassidychu/spring-starbucks-api:1.0 .
```

```sh
docker push cassidychu/spring-starbucks-api:1.0 
```

![deploy kong](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/deploy-kong.png)

**Deploy Starbucks to GKE**

1. Select Standard Cluster and not Autopilot Cluster
2. Create a new cluster
3. You can name the cluster name anything you want
4. You can leave everything else as default in the cluster basics section
  - Zonal 
  - zone:  us-central1-c
  - Release channel
  - Release channel: Regular channel (default)
  - Version: 1.25.7-gke.10000 (default)
5. Go to the Node Pools section
6. Click on Nodes
7. Keep these as default:
  - Image type: Container-Optimized OS with containerd (cos_containerd) (default)
  - Machine Configuration: General Purpose
  - Series: E2
  - Boot disk type: Balanced persistent disk 
  - Boot disk size (GB): 100
8. The only thing you change on the Nodes page is the machine type. Make the machine type smaller because it will cost less but have enough storage for what you will need. I personally changed mine to e2-small (vCPU, 2 GB memory) as it can hold a bit more than what micro offers. 
9. Click create. Be aware it may take awhile before the cluster is created.
10. While you are waiting for the cluster to be created, you can connect the cluster to the cloud shell terminal. Make sure to connect because you will get a connection error if this step is not done. 
11. Run the provided command given for connection in shell.
12. You can now begin the rest of the lab. I personally just stayed in the given directory that was provided in the shell. 

- Create the cluster 

![create cluster](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/create-cluster.png)

![create cluster part two](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/create-cluster-part-two.png)

- Changing the Node Pool 

![node pool part one](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/node-pool-part-one.png)

- I changed my machine type to e2-small (vCPU, 2 GB memory) because it is cheaper and can store more than micro.

![node pool part two](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/node-pool-part-one.png)

- THIS IS IMPORTANT DO NOT FORGET!!!

![connect](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/connect.png)

- You can run this in shell or copy and paste it into the terminal. 

![connect command](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/connect-command.png)

- Be sure to upload the file from VSCode or any text editior before running this command. In your file, change the image to the image you will be using.

```sh
kubectl create -f deployment.yaml 
```

- deployment.yaml(example)
```sh
apiVersion: apps/v1
kind: Deployment
metadata:
  name: spring-starbucks-api-deployment
  namespace: default
spec:
  selector:
    matchLabels:
      name: spring-starbucks-api
  replicas: 2 # tells deployment to run 2 pods matching the template
  template: # create pods using pod definition in this template
    metadata:
      # unlike pod.yaml, the name is not included in the meta data as a unique name is
      # generated from the deployment name
      labels:
        name: spring-starbucks-api
    spec:
      containers:
      - name: spring-starbucks-api
        image: cassidychu/spring-starbucks-api:latest
        ports:
        - containerPort: 8080
```

- I ran into the error of "does not have minimum availability." This required me to change the starbucks-api Dockerfile's profile from CMD java -jar /srv/spring-starbucks-api-1.0.jar  --spring.profiles.active=test to CMD java -jar /srv/spring-starbucks-api-1.0.jar  --spring.profiles.active=dev. I just changed it in the Dockerfile because docker compose was not needed in this lab.

```sh
docker build -t cassidychu/spring-starbucks-api:1.0 .
```

- I also ran the command below to keep checking if the status was successful. 

```sh
kubectl get pods
```

![starbucks api dockerfile](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/starbucks-api-dockerfile.png)

![deployment yaml](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/deployment-yaml.png)

**Create a Service for Starbucks API**
```sh
kubectl create -f service.yaml
```

- service.yaml(example)
```sh
apiVersion: v1
kind: Service
metadata:
  name: spring-starbucks-api-service 
  namespace: default
spec:
  type: ClusterIP
  ports:
  - port: 80
    targetPort: 8080 
  selector:
    name: spring-starbucks-api
```

![service yaml](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/service-yaml.png)


**Test Reachability from GKE Jumpbox Pod**
```sh
kubectl create -f jumpbox.yaml
```

- jumpbox.yaml
```sh
apiVersion: v1
kind: Pod
metadata:
  name: jumpbox
spec:
  containers:
  - name: jumpbox
    image: ubuntu
    imagePullPolicy: Always
    command:
    - sleep
    - "3600"
```

```sh
kubectl exec  -it jumpbox -- /bin/bash
	
apt-get update
apt-get install curl -y
curl http://spring-starbucks-api-service:80/ping

{
    "test": "Starbucks API version 2.0 alive!"
}
```

![jumpbox yaml one](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/jumpbox-yaml-one.png)

![jumpbox yaml two](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/jumpbox-yaml-two.png)

- Ran the curl test in jumpbox

![jumpbox curl](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/jumpbox-curl.png)


**Install Kong GKE Ingress Controller**
```sh
kubectl apply -f https://bit.ly/k4k8s
```

- Set PROXY_IP to Kong's Public IP, but make sure to put the kong-proxy endpoint IP that appears on your screen.  

```sh
export KONG="PUT YOUR KONG PROXY IP HERE"
echo $KONG
```

- Test Kong via Proxy IP. Should get back "no route matched..."
```sh
curl -i $KONG
```

- When you curl, "{"message":"no Route matched with those values"}" should appear. 

![apply https](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/apply-https.png)

![kong ip proxy](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/kong-ip-proxy.png)

![ip message](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/ip-message.png)

**Create an Ingress rule to proxy the Starbucks Service**
```sh
kubectl apply -f kong-ingress-rule.yaml
```

- kong-ingress-rule.yaml
- Below is what the professor provided in the assignment page. I REPEAT DO NOT USE IT!! It will give an error of "error: resource mapping not found for name: "starbucks-api" namespace: "" from "kong-ingress-rule.yaml": no matches for kind "Ingress" in version "extensions/v1beta1" ensure CRDs are installed first".

```sh
apiVersion: extensions/v1beta1
kind: Ingress
metadata:
  name: starbucks-api
  annotations:
    kubernetes.io/ingress.class: kong
spec:
  rules:
  - http:
      paths:
      - path: /api
        backend:
          serviceName: spring-starbucks-api-service
          servicePort: 80
```
- INSTEAD USE THIS FOR THE KONG-INGRESS-RULE.YAML:

```sh
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: starbucks-api
  annotations:
    kubernetes.io/ingress.class: kong
spec:
  rules:
  - http:
      paths:
      - path: /api
        backend:
          service:
            name: spring-starbucks-api-service
            port:
              number: 80
        pathType: ImplementationSpecific
```

- Using the above code for king-ingress-rule.yaml will work because it resolves the error from the ingress.yaml file from Lab #3 Gumball Demo. Kubernettes mentioned how that some things have changed, listing out requirements for Ingress such as how certain fields have been renamed and pathType is now a requirement for each specified path. [Ingress Kubernetes Changed](https://kubernetes.io/docs/reference/using-api/deprecation-guide/)

![ingress rule yaml](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/ingress-rule-yaml.png)

- Run this for kong-strip-path.yaml

```sh
kubectl apply -f kong-strip-path.yaml
```

- kong-strip-path.yaml
```sh
apiVersion: configuration.konghq.com/v1
kind: KongIngress
metadata:
  name: kong-strip-path
route:
  methods:
  - GET
  - POST
  - PUT
  - DELETE
  strip_path: true
```

```sh
kubectl patch ingress starbucks-api -p '{"metadata":{"annotations":{"konghq.com/override":"kong-strip-path"}}}'
```

![kong strip path yaml](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/kong-strip-path-yaml.png)

**Test Kong API Ping Endpoint**
- Be sure to change $KONG to the IP endpoint listed under kong-proxy in Services & Ingress.

```sh
curl $KONG/api/ping
```

![ping endpoint](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/ping-endpoint.png)

**Add Kong Key-Auth PlugIn**
```sh
cat kong-key-auth.yaml
```

```sh
kubectl apply -f kong-key-auth.yaml
```

- kong-key-auth.yaml
```sh
apiVersion: configuration.konghq.com/v1
kind: KongPlugin
metadata:
  name: kong-key-auth
plugin: key-auth
```

```sh
kubectl patch service spring-starbucks-api-service -p '{"metadata":{"annotations":{"konghq.com/plugins":"kong-key-auth"}}}'
```

![key auth yaml](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/key-auth-yaml.png)

**Configure an API Client Key**
```sh
kubectl apply -f kong-consumer.yaml
```

- kong-consumer.yaml
```sh
apiVersion: configuration.konghq.com/v1
kind: KongConsumer
metadata:
  name: apiclient
  annotations:
    kubernetes.io/ingress.class: kong
username: apiclient
```

**Create Kubernetes Secret**
```sh
 kubectl create secret generic apikey  \
  --from-literal=kongCredType=key-auth  \
  --from-literal=key=Zkfokey2311
```

![consumer secret key](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/consumer-secret-key.png)

**Apply API Key Credentials to API Client**
```sh
kubectl apply -f kong-credentials.yaml
```

- kong-credentials.yaml
```sh
apiVersion: configuration.konghq.com/v1
kind: KongConsumer
metadata:
  name: apiclient
  annotations:
    kubernetes.io/ingress.class: kong
username: apiclient
credentials:
- apikey
```

![kong credentials yaml](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/kong-credentials-yaml.png)


**Test Your API Against Kong via Public IP of Load Balancer**
- I just tested the api in the VSCode terminal since it supported http.

```sh
export KONG="PUT YOUR KONG PROXY IP HERE"
echo $KONG

curl $KONG/api/ping
curl $KONG/api/ping --header 'apikey: Zkfokey2311'

http $KONG/api/ping
http $KONG/api/ping apikey:Zkfokey2311
```

![test api](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/test-api.png)

**Test on Postman**
1. Open Postman
2. Go on Finder and look for the json files named: spring-starbucks-collection.json and starbucks-kong-environment.json
3. Copy those files and paste them onto your desktop
4. Click the import button in Postman located on the top left corner and drag + drop the files
5. Go to [Httpie](https://httpie.io/cli) and download it using the command below in your terminal:

```sh
brew install httpie
```

6. In Postman click under Kong API Gateway folder in the SPRING STARBUCKS (Kong) folder "GET Server Status" and send

![postman status one](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/postman-status-one.png)

![postman status two](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/postman-status-two.png)

![postman status three](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/postman-status-three.png)

7. Under Kong API Gateway folder in the SPRING STARBUCKS (Kong) folder click "GET Server Services" and send

![postman services one](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/postman-services-one.png)

![postman status three](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/postman-services-two.png)

8. Once that is finished downloading, use the command below to check the status of the kong port default localhost:

```sh
http :8001/status
```

![http terminal status](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/http-terminal-status.png)

![http terminal status two](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/http-terminal-status-two.png)

![http terminal status three](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/http-terminal-status-three.png)

9. Run this code next in the terminal but be sure to be in the right directory that the file is located in:

```sh
http :8801/config config=@kong.yaml
```

![http terminal config one](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/http-terminal-config-one.png)

![http terminal config two](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/http-terminal-config-two.png)

![http terminal config three](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/http-terminal-config-three.png)

10. In your terminal use:

```sh
http localhost/api/ping
```

```sh
http "PUT YOUR KONG PROXY IP HERE"/api/ping apikey:"ENTER YOUR API KEY HERE"
```
- As you can see, when you ping without the API key, it shows a message that no API key was found. But in the second command when you use the API key, it sends the test message.

![terminal ping before postman](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/terminal-ping-before-postman.png)

11. Now testing it in Postman under "GET PING" it should show the same thing that was seen in the terminal.

![postman no key](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/postman-no-key.png)

![postman with key](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/postman-with-key.png)

- This just shows that you can test it with putting the API key in the header.

![postman header](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/postman-header.png)

12. Click on View at the top navbar of your computer, select "Show Postman Console." Clear everything on the console and then go back to Postman to send the ping again.

- The console shows that the API key was used from the header.

![postman console](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/postman-console.png)

- Now testing again but remove the apikey.

![test no key](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/test-no-key.png)

- As you can see, in the Postman console, since the apikey was removed before the ping was sent, it displays as an empty header for the apikey.

![console no key](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/console-no-key.png)

12. I just tested to see that post new card and get card worked in Postman.

![post new card](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/post-new-card.png)

![get card](https://github.com/nguyensjsu/cmpe172-cassidychu5/blob/main/labs/lab8/screenshots/get-card.png)

- The changes that would be needed in order to deploy the Starbucks API with MySQL/Cloud SQL is simply changing the enivronment variable to match the Cloud SQL host. 