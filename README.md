# Seleniumproject002

Automated login and logout tests for the OrangeHRM demo site, built with Java, Selenium, and TestNG using the Page Object Model.

## Why this matters

Login is the front door of most products, and when it breaks, users notice first. This suite automates the login and logout flows of the OrangeHRM demo site. It checks that valid users get in, that bad input is rejected with the right message, and that sessions end cleanly. One Maven command runs the whole suite, so these checks can run on every change instead of depending on someone clicking through the app by hand.

## What this shows

The Page Object Model keeps locators and page actions in one class per screen, so a UI change means one fix instead of edits across every test. TestNG handles test structure and execution, which keeps the test classes focused on behavior. Maven declares the dependencies in `pom.xml`, so the same build works on any machine with Java 21 and Chrome.

## Tech stack

- Java 21
- Selenium WebDriver 4
- TestNG
- Maven
- Page Object Model (POM)
- Git and GitHub

## Prerequisites

- Java 21
- Maven
- Google Chrome
- An internet connection (the tests run against the public OrangeHRM demo site)

## Quick start

```bash
git clone https://github.com/BrightWave-100/Seleniumproject002.git
cd Seleniumproject002
mvn test
```

A passing run ends with TestNG reporting no failures and Maven printing `BUILD SUCCESS`.

## Test coverage

- **Valid login:** correct credentials take the user to the dashboard.
- **Invalid password:** a real username with a wrong password shows the "Invalid credentials" message.
- **Invalid username:** an unknown username shows the "Invalid credentials" message.
- **Empty username:** submitting only a password shows a "Required" message under the username field.
- **Empty password:** submitting only a username shows a "Required" message under the password field.
- **Empty username and password:** submitting a blank form shows two "Required" messages.
- **Logout:** a logged-in user ends the session and returns to the login page.

## Project structure

- `src/test/java/pages/` holds the Page Object classes (LoginPage, DashboardPage).
- `src/test/java/part2/` holds the TestNG test class (FirstSeleniumTest).
- `pom.xml` defines Maven dependencies and build settings.

---

Built by BrightWave-100 as a QA portfolio project.