# ContextGraph backend (`backend-graph`)

Spring Boot REST API for the ContextGraph React/Vite frontend. Java 21, Maven, PostgreSQL, JWT authentication, BCrypt password hashes, PDF text extraction, SHA-256 duplicate detection, owner-scoped document access, collections, search history, dashboard, graph and timeline endpoints.

## Requirements
- JDK 21
- Maven 3.9+
- PostgreSQL 14+ (local) or a hosted PostgreSQL database

## Configure
Set these environment variables before running:

| Variable | Required | Example |
|---|---|---|
| `DB_URL` | Yes | `jdbc:postgresql://localhost:5432/contextgraph` |
| `DB_USERNAME` | Yes | `postgres` |
| `DB_PASSWORD` | Yes | your database password |
| `JWT_SECRET` | Yes outside local development | a random secret at least 32 bytes long |
| `CORS_ALLOWED_ORIGINS` | Yes for deployment | `http://localhost:5173,https://your-frontend.vercel.app` |
| `PORT` | Optional | `8080` |
| `STORAGE_DIRECTORY` | Optional | `./data/uploads` |

The defaults are only for local development. Never commit production credentials. If credentials were previously pasted into a chat or committed, rotate them.

Create a PostgreSQL database named `contextgraph` (or adjust `DB_URL`). Flyway applies the initial schema automatically. Hibernate validates the schema instead of silently changing it.

## Run on Windows PowerShell
```powershell
# From the backend-graph folder
$env:DB_URL="jdbc:postgresql://localhost:5432/contextgraph"
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="YOUR_LOCAL_DB_PASSWORD"
$env:JWT_SECRET="replace-with-a-random-secret-at-least-32-characters-long"
$env:CORS_ALLOWED_ORIGINS="http://localhost:5173"
mvn clean package
mvn spring-boot:run
```

Health check: http://localhost:8080/api/health
Actuator health: http://localhost:8080/actuator/health

## Frontend
Set `VITE_API_BASE_URL=http://localhost:8080/api` in the frontend `.env` file and restart Vite. For Vercel, set `VITE_API_BASE_URL` to your deployed backend URL ending in `/api`, then redeploy. Configure the backend's `CORS_ALLOWED_ORIGINS` with the exact frontend origin(s).

## API endpoints
- `POST /api/auth/register` JSON `{ "name", "email", "password" }` (password 8+ chars)
- `POST /api/auth/login` JSON `{ "email", "password" }` returns `{ "token", "user" }`
- `GET /api/auth/me`, `GET/PATCH /api/profile`
- `GET /api/dashboard/stats`
- `GET /api/documents`, `POST /api/documents/upload` (multipart field `file`, PDF only, max 25 MB), `GET /api/documents/{id}`, `DELETE /api/documents/{id}`
- `GET /api/search?q=term`, `GET /api/search/history`
- `GET /api/graph`, `GET /api/timeline`
- `GET/POST /api/collections`, `PATCH/DELETE /api/collections/{id}`
- `GET /api/health`

All endpoints except registration, login and health require `Authorization: Bearer <token>`. Documents and collections are scoped to the authenticated owner. Duplicate PDF uploads by the same user return HTTP 409. Uploaded files are stored on the server filesystem; for cloud deployments, configure a persistent disk at `STORAGE_DIRECTORY` or move to object storage. Render's ephemeral filesystem can lose files on restart unless a persistent disk is attached.

## Important verification note
This source bundle is provided as a clean rebuild, but this environment did not have Maven available to execute a real dependency-resolving build. Run `mvn clean package` on your machine and share the full first error if it fails. No software can honestly be guaranteed error-free without building and testing it against your database and deployment environment.
