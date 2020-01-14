# Configuration Rasberry Pi
Installer un Base de donnée Maria DB, puis indiquer les coordonnées de la BDD dans le code : 
SystemeEmbarquee>Raspberry Pi>Projet Spring Swagger Connection Bdd>src>main>ressources> application.properties




# Watch your family

Exécuter votre DB mysql. Si vous avez docker, vous pouvez utiliser la commande suivante:
```
docker run --name mariadb --rm -e MYSQL_ROOT_PASSWORD=toor -e MYSQL_DATABASE=defaultdb -p 3306:3306 -v "`pwd`/initdb:/docker-entrypoint-initdb.d" mariadb
```







