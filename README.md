# 韩概团购系统

## 项目介绍
韩概团购是一个基于Spring Boot开发的团购系统平台，旨在提供高效、便捷的团购服务解决方案。

## 技术栈
- **后端框架**：Spring Boot 3.2.3
- **安全框架**：Spring Security
- **数据库**：MySQL
- **ORM框架**：MyBatis-Plus 3.5.10.1
- **API文档**：SpringDoc OpenAPI (Swagger) 2.3.0
- **项目构建**：Maven
- **其他工具**：
  - Lombok：简化Java代码
  - Spring Boot Validation：参数校验

## 主要功能
- 用户认证与授权
- 全局异常处理
- API接口文档自动生成
- 参数校验

## 系统要求
- JDK 17
- Maven 3.x
- MySQL 8.x

## 快速开始

### 1. 克隆项目
```bash
git clone [项目地址]
cd hangaituangou-web
```

### 2. 配置数据库
在 `src/main/resources/application.properties` 中配置数据库连接信息

### 3. 构建项目
```bash
mvn clean install
```

### 4. 运行项目
```bash
mvn spring-boot:run
```
或者直接运行 `HangaituangouApplication.java` 的 main 方法

### 5. 访问接口文档
启动项目后，访问：http://localhost:8080/swagger-ui.html

## 项目结构
```
src/
├── main/
│   ├── java/
│   │   └── com/dsy/hangaituangou/
│   │       ├── config/         # 配置类
│   │       ├── controller/     # 控制器
│   │       ├── domain/        # 领域模型
│   │       ├── exception/     # 异常处理
│   │       └── service/       # 业务逻辑
│   └── resources/             # 配置文件
└── test/                      # 测试代码
```

## 特性
- 统一的异常处理机制
- 规范的API接口文档
- 完善的参数校验
- 安全的身份认证

## 开发团队
- 开发维护：[团队/个人名称]

## 许可证
Apache License 2.0

## 联系方式
如有问题或建议，请联系：[联系方式]