FROM openjdk:17-slim

# Install Maven and wget
RUN apt-get update && \
    apt-get install -y maven wget && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Set working directory
WORKDIR /app

CMD ["tail", "-f", "/dev/null"]