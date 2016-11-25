# appium-selenium-testng-framework
All-in-one test automation framework with sample tests against Greenfield test management app.

### Highlights: 
  * Support for Mobile, Web and API testing
  * Parallel test execution
  * Support for Selenium Grid
  * Custom test listeners (support for integration with various APIs) 
  * Page Object pattern
  * Eye candy reports (with screenshots) deployed on standalone server
  
### Technology stack:
  * TestNG (test runner)
  * Appium (mobile testing)
  * Selenium (web app testing)
  * REST-assured (REST API testing)
  * Allure (reporting)

## Prerequisites
1. Deploy Greenfield app as described in https://github.com/maciejd/greenfield-django
2. Run Selenium Standalone Chrome node `docker run -d -p 4444:4444 selenium/standalone-chrome:3.0.1-aluminum`
3. Clone repo with tests `git clone https://github.com/maciejd/appium-selenium-testng-framework.git`

## Running tests

### Web tests
1. Run `mvn clean test` specyfing the following parameters:
  * remote - boolean, run tests against Remote Web Driver
  * seleniumGridURL - Selenium Grid / Standalone Node URL, e.g. `http://127.0.0.1:4444/wd/hub`
  * browser - web browser we want to test agains on, e.g. `chrome`
  * appUrl - web app base URL, e.g. `127.0.0.1`
  * suite - test suite to run, filters test by package name (see pom.xml), e.g. `web`
  
  If your docker host is at `127.0.0.1` then you'd need to execute the following:
  `mvn clean test -Dremote=true -DseleniumGridURL=http://127.0.0.1:4444/wd/hub -Dbrowser=chrome -DappUrl=127.0.0.1 -Dsuite=web`

### Mobile tests
TODO

### API tests
TODO

## Reporting
1. Generate report `mvn site`
2. Deploy jetty `mvn jetty:run`
3. Reports should be accessible on port 8080

![alt text](http://i.imgur.com/bezAgFm.png "Allure report")
