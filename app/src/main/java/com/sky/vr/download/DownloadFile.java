package com.sky.vr.download;


import java.io.File;

/**
 * Created by Aspsine on 2015/4/20.
 */
public class DownloadFile {

    private String mUri;
    private File mFolder;
    private String mName;
    private String mDescription;
    private boolean mScannable;

    private DownloadFile() {
    }

    private DownloadFile(String uri, File folder, String name, String description, boolean scannable) {
        this.mUri = uri;
        this.mFolder = folder;
        this.mName = name;
        this.mDescription = description;
        this.mScannable = scannable;
    }

    public String getUri() {
        return mUri;
    }

    public File getFolder() {
        return mFolder;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public boolean isScannable() {
        return mScannable;
    }

    public static class Builder {

        private String mUri;
        private File mFolder;
        private String mName;
        private String mDescription;
        private boolean mScannable;

        public Builder() {
        }

        public Builder setUri(String uri) {
            this.mUri = uri;
            return this;
        }

        public Builder setFolder(File folder) {
            this.mFolder = folder;
            return this;
        }

        public Builder setName(String name) {
            this.mName = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.mDescription = description;
            return this;
        }

        public Builder setScannable(boolean scannable) {
            this.mScannable = scannable;
            return this;
        }

        public DownloadFile build() {
            DownloadFile request = new DownloadFile(mUri, mFolder, mName, mDescription, mScannable);
            return request;
        }
    }
}
