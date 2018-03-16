package com.example.utils.page;

/**
 * 封装分页
 * @Company
 * @Discription
 * @Author guoxiaojing
 * @CreateDate 2018/3/16 14:47
 * @Version 1.0
 */
public class PageInfo {

    private Integer rowPageNum = 1000; //每页多少条
    private Long totalNum = 0L;//总数
    private Integer currentNum = 1;//当前页数

    /**
     * 获取分页起始位置
     * @return
     */
    public int getStart(){
        if(currentNum < 1){
            currentNum = 1;
        }
        return (currentNum-1)*rowPageNum;
    }

    /**
     * 获取总页数
     * @return
     */
    public int getPageNum(){
        int page = (int)(totalNum/rowPageNum);
        if(totalNum%rowPageNum>0) {
            page++;
        }
        return page;
    }

    /**
     * 获取结尾位置
     * @return
     */
    public long getEnd(){
        int end= getStart()+rowPageNum;
        return Math.min(end, totalNum);
    }

    public Integer getRowPageNum() {
        return rowPageNum;
    }

    public void setRowPageNum(Integer rowPageNum) {
        this.rowPageNum = rowPageNum;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(Integer currentNum) {
        this.currentNum = currentNum;
    }
}
