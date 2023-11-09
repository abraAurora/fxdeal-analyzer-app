# API Documentation

## API Endpoint: `/accept-fx-deal`

**Description**: This API endpoint allows you to submit an FX deal for validation and acceptance.

### Request

- **Method**: POST
- **Headers**:
    - `Content-Type`: application/json
- **Request Body**: The request body should contain the following parameters in JSON format.

  ```json
  {
      "dealUniqueId": "string",
      "fromCurrencyIsoCode": "string",
      "toCurrencyIsoCode": "string",
      "dealTimestamp": "string (ISO 8601 date-time)",
      "dealAmount": "number (numeric value)"
  }

*POST /accept-fx-deal*
*Content-Type: application/json*

    ```json
    {
        "dealUniqueId": "FX12345",
        "fromCurrencyIsoCode": "USD",
        "toCurrencyIsoCode": "EUR",
        "dealTimestamp": "2023-11-15T14:30:00Z",
        "dealAmount": 1000.00
    }
# Response

## Success Response (HTTP 200 OK):

**Description**: The FX deal has been accepted.
*HTTP/1.1 200 OK*

# Error Responses

- **HTTP 400 Bad Request**: Indicates invalid input data. The response contains error details.
- **HTTP 409 Conflict**: Indicates that a deal with the same ID already exists in the database.

## Example Success Response:


    ```json
    {
      "status": "DealAccepted",
      "message": "FX deal accepted successfully."
    }

*HTTP/1.1 409 Conflict*

    ```json
    {
    "status": "DealNotAccepted",
    "message": "Deal not accepted because a deal with the same ID already exists in the database."
    }

*HTTP/1.1 400 Bad Request*

    ```json
    {
    "status": "DealNotAccepted",
    "message": "Deal not accepted because At least one of the currency codes is not valid."
    }

# Additional Information

This API checks the following validations:

- `dealUniqueId` must be unique.
- `fromCurrencyIsoCode` and `toCurrencyIsoCode` must be valid currency codes.
- `dealTimestamp` must not be in the future.
- `dealAmount` must be a valid non-negative numeric value.




