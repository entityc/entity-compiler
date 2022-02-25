/*
 * Copyright (c) 2019-2022 The EntityC Project. All rights reserved.
 * Use of this file is governed by the BSD 3-clause license that
 * can be found in the LICENSE.md file in the project root.
 */

package org.entityc.compiler.util;

public enum HTTPResponseStatus {

    // 100s - Information responses
    CONTINUE(100, "Continue"),
    SWITCHING_PROTOCOL(101, "Switching Protocol"),
    PROCESSING(102, "Processing"),
    EARLY_HINTS(103, "Early Hints"),

    // 200s - Successful responses
    OK(200, "OK"),
    CREATED(201, "Created"),
    ACCEPTED(202, "Accepted"),
    NON_AUTHORITATIVE_INFORMATION(203, "Non-Authoritative Information"),
    NO_CONTENT(204, "No Content"),
    RESET_CONTENT(205, "Reset Content"),
    PARTIAL_CONTENT(206, "Partial Content"),
    MULTI_STATUS_207(207, "Multi-Status"),
    MULTI_STATUS_208(208, "Multi-Status"),
    IM_USED(226, "IM Used"),

    // 300s - Redirection messages
    MULTIPLE_CHOICE(300, "Multiple Choice"),
    MOVED_PERMANENTLY(301, "Moved Permanently"),
    FOUND(302, "Found"),
    SEE_OTHER(303, "See Other"),
    NOT_MODIFIED(304, "Not Modified"),
    TEMPORARY_REDIRECT(307, "Temporary Redirect"),
    PERMANENT_REDIRECT(308, "Permanent Redirect"),

    // 400s - Client error responses
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"),
    REQUEST_TIMEOUT(408, "Request Timeout"),
    CONFLICT(409, "Conflict"),
    GONE(410, "Gone"),
    LENGTH_REQUIRED(411, "Length Required"),
    PRECONDITION_FAILED(412, "Precondition Failed"),
    PAYLOAD_TOO_LARGE(413, "Payload Too Large"),
    URI_TOO_LONG(414, "URI Too Long"),
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
    REQUESTED_RANGE_NOT_SATISFIABLE(416, "Requested Range Not Satisfiable"),
    EXPECTATION_FAILED(417, "Expectation Failed"),
    IM_A_TEAPOT(418, "I'm a teapot"),
    MISDIRECTED_REQUEST(421, "Misdirected Request"),
    UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"),
    LOCKED(423, "Locked"),
    FAILED_DEPENDENCY(424, "Failed Dependency"),
    TOO_EARLY(425, "Too Early"),
    UPGRADE_REQUIRED(426, "Upgrade Required"),
    PRECONDITION_REQUIRED(428, "Precondition Required"),
    TOO_MANY_REQUESTS(429, "Too Many Requests"),
    REQUEST_HEADER_FIELDS_TOO_LARGE(431, "Request Header Fields Too Large"),
    UNAVAILABLE_FOR_LEGAL_REASONS(451, "Unavailable For Legal Reasons"),

    // 500s - Server Error Responses
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    NOT_IMPLEMENTED(501, "Not Implemented"),
    BAD_GATEWAY(502, "Bad Gateway"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    Gateway_Timeout(504, "Gateway Timeout"),
    HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version Not Supported"),
    NETWORK_AUTHENTICATION_REQUIRED(511, "Network Authentication Required"),
    ;

    private final int    code;
    private final String title;

    HTTPResponseStatus(int code, String title) {
        this.code = code;
        this.title = title;
    }

    public static HTTPResponseStatus FindByCode(int code) {
        for (HTTPResponseStatus responseStatus : values()) {
            if (responseStatus.code == code) {
                return responseStatus;
            }
        }
        return null;
    }

    public static HTTPResponseStatus FindByName(String name) {
        for (HTTPResponseStatus responseStatus : values()) {
            if (responseStatus.name().equals(name)) {
                return responseStatus;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }
}
