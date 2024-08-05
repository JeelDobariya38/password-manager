# Database Design

**Database**: sqlite3(master.db)

## Current Database Design (v0.1.0)

| Tables | Description |
| --- | --- |
| `passwords` | for storage password info |

### passwords table

| Fields | Property | Description 
| --- | --- | -- |
| `id` | primary key, number |
| `domain` | varchar(40) | eg, `google.com`.
| `username` | varchar(60) | can be used as email too.
| `password` | varchar(60) | actually password value.
