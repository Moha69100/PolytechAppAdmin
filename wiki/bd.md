Base de données
-------------

Nous avons choisi le SGBD  **PostgreSQL** en tant que base de données principales de notre application.
Nous avons eu que deux choix techniques possibles (en raison de leur gratuité) , MySQL et PostgreSQL.
Depuis quelques années, PostgreSQL apparait comme le SGBD libre le plus performant et fiable du marché alors que MySQL, son concurrent direct, manque de fonctionnalités majeures (backups, tunning...) 
La version utilisée est la version **9.4**

> **Note **

> - L'équipe précédente avait utilisé une version antérieure de postgreSQL, la 9.1


#### <i class="icon-file"></i> Connect to database

 Connect as **postgres** user and run **psql *database_name*** to access the database. 
 
 > **Note** 
 > Les bases de données disponibles sur le serveurs sont au nombre de 3 : Test, Qualification & Production. 
>Base de données   | Nom de la base | Utilisation 
> ------------ |-----------------
> Test |  webadmin_test| Tous les tests de types modification DDL sont lancées sur cette base. La structure peut ainsi être détruite ou modifiée sans impacter d'autres utilisateurs. 
> Qualification   | webadmin_qa |  C'est une copie de la production, les tests lancés ici permettent de valider une mise à jour en production. 
> Production | webadmin | C'est la base de données principale utilisée par l'application web pour contenir et afficher les données de la solution. 

> **Note2:** if you don't have **psql** bin in Path, add it with by running this command : 
> *export PATH=$PATH:/usr/lib/postgresql/9.3/bin*


#### <i class="icon-folder-open"></i> Switch to another database
You have to disconnect from your current session for connect to new one. 

```
\quit
psql *another_database_name*
```

#### <i class="icon-pencil"></i> Check log files 

Le serveur PostgreSQL est configuré avec un niveau de log important, cela implique de faire attention à la place des fichiers lorsque le serveur est en production. 
```
# Directory :
/usr/local/pgsql/data/pg_log 
# Récupération du dernier fichier 
ls -lrt 
# Affichage du flux du log
tail -f <nom_fichier>
```


#### <i class="icon-pencil"></i> Check number of current connections

If some development are in progress, the postgreSQL server could reach processes & resources limit. 
If you want to check the number of current connections : 
```
ps -eaf | grep postgres | grep  postgres: | grep <database_name> 
```

#### <i class="icon-hdd"></i> Export a database

You can export the database by making a dump file with pg_dump tool. 

```
pg_dump  database_name > database_name_$(date +%Y_%m_%d).sql
# Example :
pg_dump  webadmin_test > webadmin_test_$(date +%Y_%m_%d).sql
```

#### <i class="icon-hdd"></i> Restore the database

Requierements  : The server is running

```
# Connect on server with postgres user
psql -u postgres -d <database_name>

# Create the user Appuser on Server then the database
create user <user_name> with password <password_name>;
create database <database_name> owner <user_name>;
\quit

# Restore the database 
psql -d <database_name -U postgres -f <file_name>

# Exemple : 
create user appuser with password 'appuserpwd';
create database webadmin_test owner appuser;
psql -d webadmin_test -U postgres -f webadmin_test_2014_10_01.sql
```



> **Tip:** Check the errors log file. If some constraints are missing, run it one by one. 


----------
> **Date : **  06/11/2014
> **Author :** Anthony RAMPON
> **Mail: ** anthony.rampon[at]gmail.com
> **Version: ** 1.0
