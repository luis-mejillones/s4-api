package models;

public class ResponseMessage {
    private Integer status;
    private String body;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public static class Builder {
        private Integer status;
        private String body;

        public Builder withStatus(Integer status) {
            this.status = status;

            return this;
        }

        public Builder withBody(String body) {
            this.body = body;

            return this;
        }

        public ResponseMessage build() {
            ResponseMessage out = new ResponseMessage();
            out.setStatus(this.status);
            out.setBody(this.body);

            return out;
        }
    }
}
