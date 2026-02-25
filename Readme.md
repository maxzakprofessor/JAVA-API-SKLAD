# 📦 Warehouse Management System (Enterprise AI Ecosystem)

[🇷🇺 Перейти к русской версии](#-warehouse-management-system-enterprise-ai-ecosystem-ru)

A universal full-stack ecosystem for warehouse accounting and intelligent analytics. This project demonstrates the evolution of corporate architecture through three migration stages: from a flexible Python prototype to an enterprise-grade Java 25 standard and a high-performance .NET 10 API.

## 🚀 Architectural Concept: Multi-Stack Evolution
The system is designed following the **API Contract Persistence** principle: the Vue 3 frontend remains unchanged, while the backend engine can be seamlessly replaced without data loss or functional disruption.

## 🛠 Technology Stacks (Backend)
*   **.NET 10 (LTS):** ASP.NET Core, EF Core, Microsoft Identity, QuestPDF, LINQ.
*   **Java 25 (LTS):** Spring Boot 3.5, Spring Security, Hibernate (JPA), Streams API.
*   **Python 3.10:** Django REST Framework (DRF), Django ORM, SimpleJWT, ReportLab.

## 🍃 NoSQL & Audit Logging
*   **MongoDB Integration:** Implemented a high-performance logging system for tracking user actions.
*   **Activity Tracking:** Every move, income, or stock adjustment is recorded as a JSON document in MongoDB, ensuring a non-blocking audit trail and historical data persistence without overloading the primary relational DB.


## ✨ Frontend: Angular 19+ (Next-Gen Reactive UI)
*   **Signals Everywhere:** Full implementation of **Angular Signals** for fine-grained reactivity, replacing zone.js dependency where possible for ultimate performance.
*   **Modern Architecture:** Standalone Components, Control Flow Syntax (@if, @for), and Functional Interceptors.
*   **Performance:** Hybrid rendering (SSR/SSG) for instant load times and optimized Change Detection.
*   **State Management:** Reactive state handling using Signals and RxJS streams.

## 🌟 Deep Technical Expertise

### 1. Solving the N+1 Problem (DB Optimization)
Implemented SQL query minimization strategies across all ORMs:
*   **.NET:** Utilizing `.Include()` (Eager Loading) and `.AsNoTracking()` for high-speed read operations.
*   **Django:** Applying `select_related` (for Foreign Keys) and `prefetch_related`.
*   **Hibernate:** Using `JOIN FETCH` and `FetchType.LAZY` strategies.

### 2. Stateless Auth & JWT (Security)
*   Across all stacks, a unified **Stateless JWT** architecture is implemented:
    **.NET 10:** ASP.NET Core **JwtBearer Authentication** & Microsoft Identity.
    **Java 25:** **Spring Security** with **JJWT** (Java JWT) integration.
    **Python:** **Simple JWT** for Django REST Framework.

*   **JWT:** A Stateless architecture was chosen to allow horizontal API scaling in Docker containers without server session affinity.
*   **Security Flow:** Implemented Role-Based Access Control (RBAC), password hashing (BCrypt/PBKDF2), and a `needsPasswordChange` mechanism (enforced temporary password change on first login).
*   **CORS Policy:** Strict filtering of trusted Origins to protect the API from unauthorized cross-origin requests.

### 3. DevOps & Containerization
*   **Docker Multi-stage Build:** Image optimization by separating build (SDK) and runtime stages.
*   **CI/CD Ready:** Fully prepared for automated deployment via Render, Azure, Vercel, and GitHub Actions.

## 📊 Complex Business Logic
A real-time inventory balance calculation algorithm has been implemented, accounting for:
*   Primary Incomes and internal Moves between an unlimited number of warehouses.
*   **Transactional Integrity:** The database is protected against broken references (Foreign Key Constraints) and cascading errors.

## 🔗 Project Repositories
*   🚀 **.NET 10 (C# / EF Core):** [aspent_api_stock](https://github.com/maxzakprofessor/aspent_api_stock.git)
*   ☕ **Java 25 (Spring Boot 3.5):** [JAVA-API-SKLAD](https://github.com/maxzakprofessor/JAVA-API-SKLAD.git)
*   🐍 **Python (Django DRF):** [drf_api_stock_ai](github.com/maxzakprofessor/drf_api_stock_ai.git)
*   ✨ **Frontend (Angular 19+):** [angular-api-stock](https://github.com/maxzakprofessor/angular-api-sklad.git)

## 👨‍💻 Developer
**Zakiryanov M.M.**  
Fullstack Developer and System Migration Architect.  

## 📺 Video Presentation
[Watch Sklad Pro AI Demo (OneDrive)](https://1drv.ms/v/c/f07141fbcbb39609/IQCqTOpBmzeSSJnZTzPWCto4AQcHprcYbQz-J_JbNYNCcbk?e=bTkgfr)

## 🌐 Live Demo Stands
*   ☁️ **Backend ASP.NET Core API (Render):** [Render](https://aspent-api-stock.onrender.com/api/goods))
*   ☁️ **Backend (Pythonanywhere):** [Admin Panel](https://mzakiryanovgmailcom.pythonanywhere.com/admin/login/?next=/admin/)
*   ☁️ **Frontend (Vercel):** [angular-api-stock.vercel.app](https://angular-api-sklad.vercel.app/)

## ✅ Project Status
*   **Identity & JWT:** 🔐 (Stable)
*   **DB Performance (N+1 Fixed):** 🚀 (Optimized)
*   **Cloud Deployment:** ☁️ (Render/Vercel Live)
*   **AI Analysis:** 🤖 (Gemini 2.0 Integrated)

---

# 📦 Warehouse Management System (Enterprise AI Ecosystem) [RU]

[🇺🇸 Switch to English](#-warehouse-management-system-enterprise-ai-ecosystem)

Универсальная Fullstack-экосистема складского учета и интеллектуальной аналитики.  
Проект демонстрирует эволюцию корпоративной архитектуры через три этапа миграции: от гибкого прототипа на Python, к корпоративному стандарту на Java 25 и высокопроизводительному API на .NET 10.

## 🚀 Архитектурная концепция: Multi-Stack Evolution
Система спроектирована по принципу **API Contract Persistence**: фронтенд на Vue 3 остается неизменным, в то время как бэкенд-движок может быть бесшовно заменен без потери данных или функционала.

## 🛠 Технологические стеки (Backend)
*   **.NET 10 (LTS):** ASP.NET Core, EF Core, Microsoft Identity, QuestPDF, LINQ.
*   **Java 25 (LTS):** Spring Boot 3.5, Spring Security, Hibernate (JPA), Streams API.
*   **Python 3.10:** Django REST Framework (DRF), Django ORM, SimpleJWT, ReportLab.

## 🍃 NoSQL и логирование (MongoDB)
*   **Интеграция MongoDB:** Реализована система высокопроизводительного логирования (Auditing) действий пользователей.
*   **Запись событий:** Каждое действие (приемка ТМЦ, перемещение между складами или корректировка) фиксируется в виде JSON-документа. Это обеспечивает отказоустойчивость истории операций без нагрузки на основную реляционную БД.


## ✨ Frontend: Angular 19+ (Реактивность нового поколения)
*   **Angular Signals:** Полный переход на Сигналы для мелкозернистой реактивности и максимальной производительности интерфейса.
*   **Modern Core:** Использование Standalone Components, нового синтаксиса Control Flow (@if, @for) и функциональных Interceptors.
*   **Performance:** Оптимизированный рендеринг (SSR) и эффективное управление состоянием без лишних перерисовок.


## 🌟 Глубокая техническая экспертиза

### 1. Решение проблемы N+1 (Оптимизация БД)
Реализованы стратегии минимизации SQL-запросов во всех ORM:
*   **.NET:** Использование `.Include()` (Eager Loading) и `.AsNoTracking()` для высокоскоростного чтения.
*   **Django:** Применение `select_related` (для Foreign Keys) и `prefetch_related`.
*   **Hibernate:** Использование `JOIN FETCH` и стратегии `FetchType.LAZY`.

### 2. Stateless Auth & JWT (Безопасность)
*   Во всех стеках реализована единая **Stateless JWT** архитектура:
    **.NET 10:** **JwtBearer Authentication** и Microsoft Identity.
    **Java 25:** **Spring Security** с интеграцией **JJWT** (Java JWT).
    **Python:** **Simple JWT** для Django REST Framework.
    **Безопасность:** Ролевая модель (RBAC), хэширование BCrypt/PBKDF2 и принудительная смена пароля.

*   **JWT:** Выбрана Stateless-архитектура, позволяющая масштабировать API горизонтально в Docker-контейнерах без привязки к сессиям сервера.
*   **Security Flow:** Реализована ролевая модель (RBAC), хэширование паролей (BCrypt/PBKDF2) и механизм `needsPasswordChange` (обязательная смена временного пароля при первом входе).
*   **CORS Policy:** Настроена строгая фильтрация доверенных источников (Origins), защищающая API от несанкционированного доступа.

### 3. DevOps & Контейнеризация
*   **Docker Multi-stage Build:** Оптимизация образов через разделение стадий сборки (SDK) и запуска (Runtime).
*   **CI/CD Ready:** Полная готовность к автоматизированному деплою на Render, Azure, Vercel и GitHub Actions.

## 📊 Сложная бизнес-логика
Реализован алгоритм расчета остатков ТМЦ в реальном времени, учитывающий:
*   Первичные поступления (Incomes) и внутренние перемещения (Moves) между неограниченным количеством складов.
*   **Транзакционную целостность:** база защищена от «битых» ссылок (Foreign Key Constraints) и каскадных ошибок.

## 🔗 Репозитории проекта
*   🚀 **.NET 10 (C# / EF Core):** [aspent_api_stock](https://github.com/maxzakprofessor/aspent_api_stock.git)
*   ☕ **Java 25 (Spring Boot 3.5):** [JAVA-API-SKLAD](https://github.com/maxzakprofessor/JAVA-API-SKLAD.git)
*   🐍 **Python (Django DRF):** [drf_api_stock_ai](github.com/maxzakprofessor/drf_api_stock_ai.git)
*   ✨ **Frontend (Angular 19+):** [angular-api-stock](https://github.com/maxzakprofessor/angular-api-sklad.git)

## 👨‍💻 Разработчик
**Закирьянов М.М.**  
Fullstack-разработчик и архитектор миграции систем.


## 📺 Видео-презентация системы
[Смотреть демонстрацию Sklad Pro AI (OneDrive)](https://1drv.ms/v/c/f07141fbcbb39609/IQCqTOpBmzeSSJnZTzPWCto4AQcHprcYbQz-J_JbNYNCcbk?e=bTkgfr)

## 🌐 Демо-стенды в облаке
*   ☁️ **Backend ASP.NET Core API (Render):** [Render](https://aspent-api-stock.onrender.com/api/goods))
*   ☁️ **Backend (Pythonanywhere):** [Панель администратора](https://mzakiryanovgmailcom.pythonanywhere.com)
*   ☁️ **Frontend (Vercel):** [angular-api-stock.vercel.app](https://angular-api-sklad.vercel.app/)

## ✅ Статус проекта
*   **Identity & JWT:** 🔐 (Стабильно)
*   **DB Performance (N+1 Fixed):** 🚀 (Оптимизировано)
*   **Cloud Deployment:** ☁️ (Render/Vercel Live)
*   **AI Analysis:** 🤖 (Gemini 2.0 Integrated)
