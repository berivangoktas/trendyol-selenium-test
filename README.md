

### * Using Technologies

- Selenium 
- JUnit 5
- Extent Report



### Report Output → temp/trendyol-report.html
### ScreenShot Output → temp/test_sessionId.png
### Project Run test 
maven clean install test

### Run test select browser
for Chrome Driver = -Dbrowser.type=CHROME

for Firefox Driver = -Dbrowser.type=FIREFOX

### Run test select platform
for local = -Dplatform.type=local

for grid =  -Dplatform.type=grid

### Run test selenium grid start on docker
docker compose -f docker-compose-v3.yml up --scale chrome=4

### Selenium Grid UI link
http://localhost:4444/ui


