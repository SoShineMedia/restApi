# Introduction

# System Requirement

# How to start the application
1. Clone the git repo
1. mvn clean install
1. mvn spring-boot:run

# Application Overview

![basic application diagram](docs/pyngo_basics.png)

### Offer

### Acceptable
An offer is acceptable if the user is at the location.

### Accepted
After an offer is accepted the duration timer starts. If the user leaves the location the timer will stop. Offers can have more acceptances than the specified number of takers. This is resolved based on the first to complete. only completed acceptances will get a reward.

# Features
* Location based points earning.
* Contact tracing.
* Points can be a store of value and a medium of exchange.
* Points can be redeemed for cash.
* In app point rewards.
* AR graphics pinned and viewable from different locations.
* Points convertible to Crypto token.
* Geographic tokenization using NFT.
* Steps counter.

# Road Map
* Deploy the application for testing
* Add pedometer
* Add push notification