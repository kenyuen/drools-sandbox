# drools-sandbox
Exploration of drools via [Drools.org](https://docs.drools.org/8.44.0.Final/drools-docs/drools/getting-started/index.html)

Note if running this on codespace ensure default to jdk 17 on the container:

```
sdk install java 17.0.10-amzn 
```

If running local with H2, access to webconsole as:
http://localhost:8080/h2-console/ 

## Sample Email Request
```json
{
    "mailbox":"mbox1",
    "senderEmail":"john@smith.com",
    "subject":"Team A"
}
```
## Expected Response
```json
{
    "mailbox": "mbox1",
    "senderEmail": "john@smith.com",
    "subject": "Team A",
    "teamAssigned": "Team_Default"
}
```