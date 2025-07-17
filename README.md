# Patient Service API

## Endpoints
- **POST /patient** – Create a new patient
- **GET /patient/{id}** – Retrieve a patient by ID

## Example JSON for `POST /patient`
```json
{
  "name": "Juan Pérez",
  "email": "marcocasta03@gmail.com",
  "address": "Av. Corrientes 1234, CABA, Argentina",
  "phone": "+54 9 11 1234-5678",
  "documentPath": "/uploads/patients/juan_perez/dni.pdf"
}
```
## Running the app
```bash
docker compose up --build
```
### The API will be available at http://localhost:8080

## Document Photo Handling
#### We simulate document photo uploads by using a file path (e.g. /uploads/patients/.../dni.pdf) instead of integrating with S3.

## Tests
#### Tests for the patient service are located at PatientTests inside test folder