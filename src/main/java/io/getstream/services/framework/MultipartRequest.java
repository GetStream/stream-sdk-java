package io.getstream.services.framework;

/**
 * Marks a generated request model whose operation is sent as multipart/form-data.
 *
 * <p>The generator adds it to the request models of upload endpoints (CHA-5084). {@link
 * StreamRequest} still encodes each upload type explicitly.
 */
public interface MultipartRequest {}
