

# Deployment

To get the pipeline going there are a few steps I had to make first:


* Deploy application manually
* Deploy Database manually
* Automate deployments
* Build pipeline

Below is a quick breakdown of each step:

The app was deployed on an ubuntu 22.04 lts 

Working app manual deployment 

```
#!/bin/bash

#update and upgrade

sudo apt update -y
sudo DEBIAN_FRONTEND=noninteractive apt-get upgrade -y

# change restart function to auto 
sed -i "s/#\$nrconf{restart} = 'i';/\$nrconf{restart} = 'a';/" /etc/needrestart/needrestart.conf

# update and upgrade without user intervention
sudo apt update -y
sudo DEBIAN_FRONTEND=noninteractive apt-get upgrade -y

# install nginx
sudo apt install nginx -y
sudo sed -i "s|try_files .*;|proxy_pass http://127.0.0.1:3000;|g" /etc/nginx/sites-available/default

# enable nginx and be able to restart 
sudo systemctl restart nginx
sudo systemctl enable nginx

# download node.js
curl -fsSL https://deb.nodesource.com/setup_20.x | sudo -E bash - &&\

# installl node.js
sudo apt-get install -y nodejs

# Install Node.js and npm
curl -sL https://deb.nodesource.com/setup_20.x | sudo -E bash -
sudo apt-get install -y nodejs

# Git clone
git clone https://github.com/im-e/traffic-risk-app.git

# Cd into app folder
Cd traffic-risk-app/frontend/src

# install npm
Npm install 

# start npm
Npm start

```
## Working app
This will allow you to go to your ip address:3000 and see the below:
![alt text](deployment-project/Manual_deployment.png)



# The Build Server

The piepline would be hosted on a build server.
To create this a micro instance was used with port `8080` for jenkins created.




## Install jenkins


```
# Get env right

sudo apt update -y
sudo DEBIAN_FRONTEND=noninteractive apt-get upgrade -y

# Install java

sudo apt update
sudo apt install fontconfig openjdk-17-jre
java -version
openjdk version "17.0.8" 2023-07-18
OpenJDK Runtime Environment (build 17.0.8+7-Debian-1deb12u1)
OpenJDK 64-Bit Server VM (build 17.0.8+7-Debian-1deb12u1, mixed mode, sharing)

# Install jenkins



sudo wget -O /usr/share/keyrings/jenkins-keyring.asc \
  https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key
echo "deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc]" \
  https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
  /etc/apt/sources.list.d/jenkins.list > /dev/null
sudo apt-get update
sudo apt-get install jenkins

```


## Start Jenkins

```
# You can enable the Jenkins service to start at boot with the command:
sudo systemctl enable jenkins


# You can start the Jenkins service with the command:
sudo systemctl start jenkins


# You can check the status of the Jenkins service using the command:
sudo systemctl status jenkins


# Get password
sudo cat /var/lib/jenkins/secrets/initialAdminPassword

```

As you can see below: Jenkins is running.

![alt text](<deployment-project/sudo systemctl stati.png>)



Once jenkins was Installed on the server I was able to go to the url and continue the configurations

![alt text](<deployment-project/Getting Started.png>)

![alt text](<Welcome to Jenkinst.png>)



![alt text](<deployment-project/Install Name.png>)


Security > git host key verification config > accept first connection

Install any dependencies on server eg node etc


##  testing the server

I then ran a quick test

![alt text](<deployment-project/Screenshot 2024-06-27 at 12.24.08.png>)






##  references

https://www.jenkins.io/doc/book/installing/linux/#debianubuntu
