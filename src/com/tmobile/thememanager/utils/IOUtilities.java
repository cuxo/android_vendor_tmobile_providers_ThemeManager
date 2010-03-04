package com.tmobile.thememanager.utils;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtilities {
    public static void close(Closeable stream) {
        try {
            stream.close();
        } catch (IOException e) {}
    }

    /**
     * @see #connectIO(InputStream, OutputStream, int)
     */
    public static int connectIO(InputStream in, OutputStream out) throws IOException {
        return connectIO(in, out, 4096);
    }

    /**
     * Connects an input and output stream together to copy all bytes from the
     * input into the output.
     * <p>
     * Does not close either stream in any case. The inputstream is left fully
     * exhausted on return.
     * 
     * @param in
     * @param out
     * @param bufSize buffer size used during copy.
     * 
     * @return number of bytes copied.
     */
    public static int connectIO(InputStream in, OutputStream out, int bufSize)
            throws IOException {
        int total = 0;
        int n;
        byte[] buf = new byte[bufSize];

        while ((n = in.read(buf)) >= 0) {
            out.write(buf, 0, n);
            total += n;
        }

        return total;
    }

    /**
     * Call {@link File#renameTo(File)}, but throw an IOException on failure.
     * 
     * @throws IOException Thrown if the rename attempt fails.
     */
    public static void renameExplodeOnFail(File src, File dst) throws IOException {
        if (!src.renameTo(dst)) {
            throw new IOException("Unable to rename '" + src + "' to '" + dst + "'");
        }
    }
}
