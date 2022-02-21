# Klutter

Klutter.io is a webservice that accepts a URL from a user and returns
a de-cluttered, ad-free and readability.js inspired simple format or
PDF for use with e-ink and e-reader devices like the remarkable or
kindle readers. 

Jira project is located here: 
https://davedavis.atlassian.net/jira/software/c/projects/PDFIT/boards/4/backlog

## Application Architecture 



[<img alt="architecture" src="docs/images/architecture draft 1.png" />]



## In Mem testing DB
- http://localhost:8080/h2-console/
- jdbc:h2:mem:testdatabase
- No user or pass.

## Sprint 0 requirements
- Introduction to Application (Overall),
- List of User Stories (Overall),
- Demo of User Stories for one
- Microservice (CRUD Actions)
- Report and Screencast required


## Sprint 0 strategy
- Build out the declutter service using the Jsoup and r4j libraries.
- Receive a URL in either the body or a URL parameter
- Set up API V1
- Add decluttered resource to the DB
- Add ONAP standard CRUD methods
- 


## ToDo
Add user, title, excerpt, byline, content, ease, grade and tag array to model.



## Ideas 
- WaveNet service
- Text analytics and sentiment analysis service