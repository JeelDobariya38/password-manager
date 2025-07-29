# Database Design & Guide

In this file, You will find the general structure & format in which app stores users data. Also you will you will find different schema of database.

> Some feature are not supported in sqlite3 and some might be specfic to some database. but as a developer we will try our best to abstract away database concept (from frontend) as much as possible by seperating database functainality in a seperate module. and frontend will just call as simple as method like savePassword() or loadPassword() or something along that lines... to communicate with database...

> [!NOTE]
> Database schema versioning system is completely separate from the app versioning system.


---


## Master Database (master.db)

**Database**: Sqlite3(`master.db`)

It is a database where user info is stored. It schema changes often between the app versions, based on changes in feature requirements. Here, in this section you will find all the version of database schema & will also find info about how we adopated to the new schema. 

### Current Database Design (v1)

| Tables      | Description                  |
| ----------- | ---------------------------- |
| `passwords` | For storing password entity. |

#### passwords table

| Fields       | Property     | Constraints                    | Description                                                                                             |
| ------------ | ------------ | ------------------------------ | ------------------------------------------------------------------------------------------------------- |
| `id`         | Integer      | PRIMARY KEY, AUTOINCREMENT     | --                                                                                                      |
| `domain`     | Text         | NOT NULL                       | domain/platform name to which password enitity is associated with.                                      |
| `username`   | Text         | NOT NULL                       | username on that domain / platform. email can be even used as a value.                                  |
| `password`   | Text         | NOT NULL                       | password for that specfic username on that specfic domain / platform.                                   |
| `notes`      | Text         | NOT NULL                       | notes that you wanna take for that record. more like be some information about account on that platform |
| `created_at` | Text         | DEFAULT CURRENT_TIMESTAMP      | --                                                                                                      |
| `updated_at` | Text         | DEFAULT CURRENT_TIMESTAMP      | --                                                                                                      |
