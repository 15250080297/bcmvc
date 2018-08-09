package com.bc.dto;

import com.bc.common.enums.RouterEnum;

import java.util.ArrayList;
import java.util.List;

public class MenuDto implements Cloneable {

    private String pid;
    private Integer id;
    private String path;
    private String redirect;
    private String name;
    private boolean hidden;
    private MenuMetaDto meta;
    private List<MenuDto> children;
    private Integer sort=50;

    public static List<MenuDto> menus(){
        List<MenuDto> menuDtoList = new ArrayList<>();


        for(RouterEnum routerEnum:RouterEnum.values()){
            if(routerEnum.getPid()!=-1){
               continue;
            }

            MenuDto  menuDto = new MenuDto();
            menuDto.setId(routerEnum.getId());
            menuDto.setPath("/"+routerEnum.getPath());
            menuDto.setName(routerEnum.getPath());
            menuDto.setHidden(routerEnum.isHidden());

            MenuMetaDto menuMetaDto=new MenuMetaDto();
            menuMetaDto.setTitle(routerEnum.getTitle());
            menuMetaDto.setIcon(routerEnum.getIcon());
            menuMetaDto.setPid(routerEnum.getPid());
            menuDto.setMeta(menuMetaDto);

            menuDto.setChildren(findChilds(routerEnum.getId()));
            menuDtoList.add(menuDto);
        }


        return menuDtoList;
    }


    private static List<MenuDto> findChilds(int id){
        List<MenuDto> childs=new ArrayList<>();
        for(RouterEnum routerEnum:RouterEnum.values()){
            if(routerEnum.getPid()==id){
                MenuDto  menuDto = new MenuDto();

                menuDto.setId(routerEnum.getId());
                menuDto.setPath("/"+routerEnum.getPath());
                menuDto.setName(routerEnum.getPath());
                menuDto.setHidden(routerEnum.isHidden());
                MenuMetaDto menuMetaDto=new MenuMetaDto();
                menuMetaDto.setTitle(routerEnum.getTitle());
               // menuMetaDto.setIcon("table");
                menuMetaDto.setPid(routerEnum.getPid());

                menuDto.setMeta(menuMetaDto);
                menuDto.setChildren(new ArrayList<>());
                childs.add(menuDto);
            }
        }
        return childs;
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
