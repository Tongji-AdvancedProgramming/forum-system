FROM ubuntu:latest
LABEL authors="cinea"

ENTRYPOINT ["top", "-b"]
