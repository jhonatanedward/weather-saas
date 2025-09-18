### Weather Subscription Service API Documentation 🌤️

This document describes the API endpoints for managing user subscriptions, including creating new subscriptions, fetching their status, and processing payments.

The base URL for all endpoints is: `http://localhost:8080/v1/subscriptions`

-----

### 1\. Create a New Subscription

Creates a new subscription for a user and returns a checkout URL.

* **Endpoint:** `POST /v1/subscriptions`
* **Request Body:** `application/json`

<!-- end list -->

```json
{
  "user_id": 12345,
  "plan": "PREMIUM"
}
```

* **Parameters:**

    * `user_id` (Long): The ID of the user for whom the subscription will be created.
    * `plan` (String): The desired subscription plan (e.g., `"FREE"`, `"PREMIUM"`).

* **Responses:**

    * **`201 Created`**: Subscription created successfully.
        * **Response Body:**
      <!-- end list -->
      ```json
      {
        "message": "Subscription created successfully.",
        "data": {
          "checkout_url": "https://mock-checkout.com/session?id=...",
          "subscription_id": "cd061d24-..."
        }
      }
      ```
    * **`400 Bad Request`**: Invalid request payload.

-----

### 2\. Get Subscription Details

Returns the details of a user's subscription based on their user ID.

* **Endpoint:** `GET /v1/subscriptions`

* **Request Parameters:**

    * `user_id` (Long): The ID of the user whose subscription will be fetched.

* **Responses:**

    * **`200 OK`**: Subscription found and returned successfully.
        * **Response Body:**
      <!-- end list -->
      ```json
      {
        "message": "Ok",
        "data": {
          "id": "cd061d24-440c-460b-9278-7099f3d587f5",
          "user_id": 1,
          "plan": "PREMIUM",
          "external_subscription_id": "sub_5235644374926592232",
          "status": "ACTIVE",
          "payment_date": "2026-09-15T13:30:00",
          "checkout_url": "https://mock-checkout.com/session?id=..."
        }
      }
      ```
    * **`404 Not Found`**: Subscription not found for the given `user_id`.

-----

### 3\. Receive Subscription Payment

This endpoint processes a payment webhook, updating the subscription status after payment confirmation.

* **Endpoint:** `POST /v1/subscriptions/payment-received`
* **Request Body:** `application/json`

<!-- end list -->

```json
{
  "subscription_id": "cd061d24-440c-460b-9278-7099f3d587f5",
  "status": "PAID",
  "payment_date": "2025-09-15T13:30:00"
}
```

* **Responses:**
    * **`200 OK`**: Payment processed and subscription status updated.
    * **`400 Bad Request`**: Invalid or malformed request payload.