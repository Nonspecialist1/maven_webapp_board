package com.koreait.board4.model;

public class BoardParamVO {
    private int recordCnt;
    private int page;
    private int sIdx;

    public int getRecordCnt() { return recordCnt; }
    public void setRecordCnt(int recordCnt) { this.recordCnt = recordCnt; }

    public int getsIdx() {
        return sIdx;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
        this.sIdx = (page -1) * recordCnt;
    }
}
