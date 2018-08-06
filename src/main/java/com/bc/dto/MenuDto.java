package com.bc.dto;

import java.util.ArrayList;
import java.util.List;

public class MenuDto {

    private String pid;
    private Integer id;
    private String path;
    private String redirect;
    private String name;
    private boolean hidden;
    private MenuMetaDto meta;
    private List<MenuDto> children;
    private Integer sort;

    public static List<MenuDto> menus(){
        List<MenuDto> menuDtoList = new ArrayList<>();

        //plugins
        MenuDto plugins=new MenuDto();

        plugins.setPid(null);
        plugins.setId(8);
        plugins.setPath("/plugins");
        plugins.setRedirect(null);
        plugins.setName("plugins");
        plugins.setHidden(false);

        MenuMetaDto pluginsMetaDto=new MenuMetaDto();
        pluginsMetaDto.setTitle("插件Demo");
        pluginsMetaDto.setIcon("table");
        pluginsMetaDto.setPrivilege(null);
        pluginsMetaDto.setPid(-1);
        pluginsMetaDto.setSort(65);
        plugins.setMeta(pluginsMetaDto);

        List<MenuDto> pluginsChilds=new ArrayList<>();
        MenuDto demolist=new MenuDto();
        demolist.setId(9);
        demolist.setPath("/demolist");
        demolist.setName("demolist");
        demolist.setHidden(false);
        MenuMetaDto demolistMeta=new MenuMetaDto();
        demolistMeta.setTitle("Demo");
        demolistMeta.setIcon("table");
        demolistMeta.setPrivilege(null);
        demolistMeta.setPid(8);
        demolistMeta.setSort(60);
        demolist.setMeta(demolistMeta);
        demolist.setChildren(new ArrayList<>());
        demolist.setSort(60);
        pluginsChilds.add(demolist);

        plugins.setChildren(pluginsChilds);
        plugins.setSort(65);
        menuDtoList.add(plugins);

        return menuDtoList;
    }


    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public MenuMetaDto getMeta() {
        return meta;
    }

    public void setMeta(MenuMetaDto meta) {
        this.meta = meta;
    }

    public List<MenuDto> getChildren() {
        return children;
    }

    public void setChildren(List<MenuDto> children) {
        this.children = children;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
