# Database Design & Guide

In this file, You will find the general structure & format in which app stores users data. Also you will you will find different schema of database.

> Some feature are not supported in sqlite3 and some might be specfic to some database. but as a developer we will try our best to abstract away database concept (from frontend) as much as possible by seperating database functainality in a seperate module. and frontend will just call as simple as method like savePassword() or loadPassword() and something along that lines. to communicate with db.

> [!NOTE]
> Database schema versioning system is completely serperte from the app versioning system.

**Database**: Sqlite3(`master.db`)

## master.db

It is a database where user info is stored. It schema changes often between the app version based on change in business requirements. Here, in this section you will find all the version of database schema & will also find info on how we adopated to the new schema. 

---

### Current Database Design (v1)

| Tables      | Description                  |
| ----------- | ---------------------------- |
| `passwords` | For storing password entity. |

#### passwords table

| Fields      | Property     | Constraints                    | Description                                                          |
| ----------- | ------------ | ------------------------------ | -------------------------------------------------------------------- |
| `id`        | Integer      | PRIMARY KEY, AUTOINCREMENT     | --                                                                   |
| `domain`    | VarChar(40)  | NOT NULL                       | domain/platform name to which password enitity is associated with.   |
| `username`  | VarChar(60)  | NOT NULL                       | username on that domain/platform. email can be even used as a value. |
| `password`  | VarChar(60)  | NOT NULL                       | password on that domain/platform                                     |
| `notes`     | VarChar(100) | --                             | --                                                                   |
| `createdat` | Date         | DEFAULT CURRENT_TIMESTAMP      | --                                                                   |
| `updatedat` | Date         | DEFAULT CURRENT_TIMESTAMP      | --                                                                   |
