package com.bc.common.enums;

public enum RouterEnum {



    /**
     * 100 + demo
     */
    DEMO(100,-1,"plugins","插件Demo",false,"table"),
         DEMO_LIST(101,100,"demolist","Demo",false,null),


    /**
     * 1000 + tool 后端工具
     */
    TOOL(1000,-1,"tool","后端工具",false,"user"),
        TOOL_LUCENE(1001,1000,"luceneMgr","Lucene更新",false,""),
        TOOL_DUTY(1002,1000,"dutyMgr","任务管理",false,""),
        RECONCILITION_FILES(1003,1000,"reconcilitionFilesMgr","对账文件管理",false,""),


    /**
     * 2000 + balance 任务管理
     */
    BALANCE(2000,-1,"balance","资产",false,"eye"),
         BALANCE_MGR(2001,2000,"balancelist","资产列表",false,""),


    /**
     * 3000 + balance 任务管理
     */
    REPORT(3000,-1,"report","报表",false,"eye"),
        SPAY_MGR(3001,3000,"spayMgr","交易统计",false,""),

    /**
     * 4000 + 币管理
     */
    COINS(4000,-1,"coins","币管理",false,"eye"),
    COINS_USERS(4001,4000,"cUserMgr","用户管理",false,""),
    COINS_SUBUSERS(4002,4000,"csubUserMgr","子账户管理",false,""),
    COINS_CHARGE_BILL(4003,4000,"cchargeBillMgr","充值管理",false,""),
    COINS_TRANSFER_BILL(4004,4000,"ctransferMgr","打款管理",false,""),


    ;

    private String path;
    private int id;
    private int pid;
    private String title;
    private boolean hidden;
    private String icon;

    private  RouterEnum(int id,int pid,String path,String title,boolean hidden,String icon){
        this.id=id;
        this.pid=pid;
        this.path=path;
        this.title=title;
        this.hidden=hidden;
        this.icon=icon;
    }



    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
