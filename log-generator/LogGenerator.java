void main()
    throws IOException, InterruptedException {
    var uuid = UUID.randomUUID().toString();

    var fileName = "/opt/app/logs/log_output.txt";
    var path = Paths.get(fileName);

    while (true) {
        var logMessage = "%s: %s\n".formatted(LocalDateTime.now(), uuid);

        try (
            var fileStream = Files.newOutputStream(
                path,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
            )
        ) {
            fileStream.write(logMessage.getBytes());
        }

        Thread.sleep(5000);
    }
}
