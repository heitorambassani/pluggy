Pluggy Heitor

Overview

Pluggy Heitor is a Kotlin-based web service developed using Java 17. The project provides two endpoints to facilitate integration with Pluggy, a financial data aggregator.

Endpoints
1. Get Institution Name and Widget URL

Endpoint: 

GET [https://pluggy-heitor-app-aa3811a86a40.herokuapp.com/widget]

Description: Retrieve URL to open widget in a new window

2. Receive Pluggy Event Webhook and Save Data

Endpoint: 

POST [https://pluggy-heitor-app-aa3811a86a40.herokuapp.com/widget]

Body (example):

{
    "itemId": "6d5aaaee-630d-4205-b242-b5f8af3806f9",
    "event": "item/created",
    "id": "6d5aaaee-630d-4205-b242-b5f8af3806f9",
    "eventId": "12ac7a26-59d1-49e3-899a-877701f6b61a",
    "triggeredBy": "CLIENT"
}

Description: Handle event webhooks from Pluggy, processing and saving relevant data in a postgres database.

Getting Started

Clone the repository.

Configure the application with necessary environment variables, database connection details, and Pluggy API credentials.

Start the application.
