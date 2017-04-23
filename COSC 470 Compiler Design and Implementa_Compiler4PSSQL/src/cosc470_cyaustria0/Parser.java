/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cosc470_cyaustria0;
import java.io.*;
import java.util.*;

/**
 *
 * @author Catherine Austria on 02/4/16.
 */
public class Parser{
    private static int[][] action = new int[][]{//<editor-fold desc="Too Long for words. Doesn't have Go To">
    /*0*/        {8,34,35,4,5,32,30,11,31,33,13,14,25,20,24,6,7,1,2,3,9,10,12,15,16,17,18,19,21,22,23,36,26,28,27,29},
    /*1*/        {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,1,2,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
    /*2*/        {-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2},
    /*3*/        {-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3},
    /*4*/        {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
    /*5*/        {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,6,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
    /*6*/        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    /*7*/        {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,7,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
    /*8*/        {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,8,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
    /*9*/        {99,99,99,9,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
    /*10*/        {-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,10,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6},  //er? [9][5]
    /*11*/        {-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4,-4},
    /*12*/        {99,99,99,99,12,99,99,13,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
    /*13*/        {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,14,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
    /*14*/        {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,15,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
    /*15*/        {-8,-8,-8,-8,-8,-8,-8,-8,-8,-8,-8,-8,-8,-8,-8,-8,-8,-8,-8,-8,-8,-8,-8,-8,-8,-8,-8,-8,-8,-8,-8,-8,-8,-8,-8,-8},  //er?[14][15]
    /*16*/        {-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5},
    /*17*/        {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,18,99,99,99,99,99,99,17,99,99,99,99,99,99,99,99,99,99,99,99,99},
    /*18*/        {-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,10,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6,-6},
    /*19*/        {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,18,99,99,99,21,99,99,99,99,99,22,23,24,99,99,99,99,99,99,99,99},
    /*20*/        {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,29,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
    /*21*/        {99,99,99,99,99,99,99,13,99,99,30,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
    /*22*/        {99,99,99,99,99,99,99,99,99,99,99,99,99,31,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
    /*23*/        {99,99,99,32,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
    /*24*/        {99,99,99,33,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
    /*25*/        {99,99,99,34,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
    /*26*/        {-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15,-15},
    /*27*/        {99,99,99,99,99,99,99,99,99,99,99,36,99,99,99,99,35,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
    /*28*/        {-12,-12,-12,-12,-12,-12,-12,-12,-12,-12,-12,-12,-12,-12,-12,-12,-12,-12,-12,-12,-12,-12,-12,-12,-12,-12,-12,-12,-12,-12,-12,-12,-12,-12,-12,-12},
    /*29*/        {-14,-14,-14,-14,-14,-14,-14,-14,-14,-14,-14,-14,-14,-14,-14,-14,-14,-14,-14,-14,-14,-14,-14,-14,-14,-14,-14,-14,-14,-14,-14,-14,-14,-14,-14,-14},
    /*30*/        {37,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
    /*31*/        {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,38,39,99,99,99,99,99,99,99,99,99,99,99},
    /*32*/        {99,99,45,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,41,99,99,99,99,99,99,99,99,42,43,44,99,99,99,99,99},
    /*33*/        {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,51,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
    /*34*/        {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,52,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
    /*35*/        {99,99,45,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,41,99,99,99,99,99,99,99,99,42,43,44,99,99,99,99,99},
    /*36*/        {-11,-11,-11,-11,-11,-11,-11,-11,-11,-11,-11,-11,-11,-11,-11,-11,-11,-11,-11,-11,-11,-11,-11,-11,-11,-11,-11,-11,-11,-11,-11,-11,-11,-11,-11,-11},
    /*37*/        {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,18,99,99,99,21,99,99,99,99,99,22,23,24,99,99,99,99,99,99,99,99},
    /*38*/        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
    /*39*/        {-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9},
    /*40*/        {-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10},
    /*41*/        {99,99,99,99,99,99,99,99,99,99,99,55,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
    /*42*/        {-27,-27,-27,-27,-27,-27,-27,-27,-27,-27,-27,-27,-27,-27,-27,-27,-27,-27,-27,-27,-27,-27,-27,-27,-27,-27,-27,-27,-27,-27,-27,-27,-27,-27,-27,-27},
    /*43*/        {-28,-28,-28,-28,-28,-28,-28,-28,-28,-28,-28,-28,-28,-28,-28,-28,-28,-28,-28,-28,-28,-28,-28,-28,-28,-28,-28,-28,-28,-28,-28,-28,-28,-28,-28,-28},
    /*44*/        {-29,-29,-29,-29,-29,-29,-29,-29,-29,-29,-29,-29,-29,-29,-29,-29,-29,-29,-29,-29,-29,-29,-29,-29,-29,-29,-29,-29,-29,-29,-29,-29,-29,-29,-29,-29},
    /*45*/        {-30,-30,-30,-30,-30,-30,-30,-30,-30,-30,-30,-30,-30,-30,-30,-30,-30,-30,-30,-30,-30,-30,-30,-30,-30,-30,-30,-30,-30,-30,-30,-30,-30,-30,-30,-30},
    /*46*/        {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,56,99,99,99,99},
    /*47*/        {-19,-19,-19,-19,-19,-19,-19,-19,-19,-19,-19,-19,-19,-19,-19,-19,-19,-19,-19,-19,-19,-19,-19,-19,-19,-19,-19,-19,-19,-19,-19,-19,-19,-19,-19,-19},
    /*48*/        {-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20},
    /*49*/        {-21,-21,-21,-21,-21,-21,63,-21,64,-21,-21,-21,61,-21,57,-21,-21,-21,-21,-21,-21,-21,-21,-21,-21,-21,-21,-21,-21,-21,-21,-21,58,59,60,62},
    /*50*/        {-23,69,-23,-23,-23,67,-23,-23,-23,68,-23,-23,-23,-23,-23,-23,-23,-23,-23,-23,-23,-23,-23,-23,-23,-23,-23,-23,-23,-23,-23,-23,-23,-23,-23,-23},
    /*51*/        {-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,-25,-25},
    /*52*/        {99,99,99,99,71,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
    /*53*/        {99,99,99,99,72,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
    /*54*/        {99,99,99,99,73,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
    /*55*/        {-13,-13,-13,-13,-13,-13,-13,-13,-13,-13,-13,-13,-13,-13,-13,-13,-13,-13,-13,-13,-13,-13,-13,-13,-13,-13,-13,-13,-13,-13,-13,-13,-13,-13,-13,-13},
    /*56*/        {-7,-7,-7,-7,-7,-7,-7,-7,-7,-7,-7,-7,-7,-7,-7,-7,-7,-7,-7,-7,-7,-7,-7,-7,-7,-7,-7,-7,-7,-7,-7,-7,-7,-7,-7,-7},
    /*57*/        {99,99,74,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
    /*58*/        {-32,-32,-32,-32,-32,-32,-32,-32,-32,-32,-32,-32,-32,-32,-32,-32,-32,-32,-32,-32,-32,-32,-32,-32,-32,-32,-32,-32,-32,-32,-32,-32,-32,-32,-32,-32},
    /*59*/        {-33,-33,-33,-33,-33,-33,-33,-33,-33,-33,-33,-33,-33,-33,-33,-33,-33,-33,-33,-33,-33,-33,-33,-33,-33,-33,-33,-33,-33,-33,-33,-33,-33,-33,-33,-33},
    /*60*/        {-34,-34,-34,-34,-34,-34,-34,-34,-34,-34,-34,-34,-34,-34,-34,-34,-34,-34,-34,-34,-34,-34,-34,-34,-34,-34,-34,-34,-34,-34,-34,-34,-34,-34,-34,-34},
    /*61*/        {-35,-35,-35,-35,-35,-35,-35,-35,-35,-35,-35,-35,-35,-35,-35,-35,-35,-35,-35,-35,-35,-35,-35,-35,-35,-35,-35,-35,-35,-35,-35,-35,-35,-35,-35,-35},
    /*62*/        {-36,-36,-36,-36,-36,-36,-36,-36,-36,-36,-36,-36,-36,-36,-36,-36,-36,-36,-36,-36,-36,-36,-36,-36,-36,-36,-36,-36,-36,-36,-36,-36,-36,-36,-36,-36},
    /*63*/        {-37,-37,-37,-37,-37,-37,-37,-37,-37,-37,-37,-37,-37,-37,-37,-37,-37,-37,-37,-37,-37,-37,-37,-37,-37,-37,-37,-37,-37,-37,-37,-37,-37,-37,-37,-37},
    /*64*/        {-38,-38,-38,-38,-38,-38,-38,-38,-38,-38,-38,-38,-38,-38,-38,-38,-38,-38,-38,-38,-38,-38,-38,-38,-38,-38,-38,-38,-38,-38,-38,-38,-38,-38,-38,-38},
    /*65*/        {-39,-39,-39,-39,-39,-39,-39,-39,-39,-39,-39,-39,-39,-39,-39,-39,-39,-39,-39,-39,-39,-39,-39,-39,-39,-39,-39,-39,-39,-39,-39,-39,-39,-39,-39,-39},
    /*66*/        {99,99,45,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,41,99,99,99,99,99,99,99,99,42,43,44,99,99,99,99,99},
    /*67*/        {99,99,45,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,41,99,99,99,99,99,99,99,99,42,43,44,99,99,99,99,99},
    /*68*/        {-40,-40,-40,-40,-40,-40,-40,-40,-40,-40,-40,-40,-40,-40,-40,-40,-40,-40,-40,-40,-40,-40,-40,-40,-40,-40,-40,-40,-40,-40,-40,-40,-40,-40,-40,-40},
    /*69*/        {-41,-41,-41,-41,-41,-41,-41,-41,-41,-41,-41,-41,-41,-41,-41,-41,-41,-41,-41,-41,-41,-41,-41,-41,-41,-41,-41,-41,-41,-41,-41,-41,-41,-41,-41,-41},
    /*70*/        {-42,-42,-42,-42,-42,-42,-42,-42,-42,-42,-42,-42,-42,-42,-42,-42,-42,-42,-42,-42,-42,-42,-42,-42,-42,-42,-42,-42,-42,-42,-42,-42,-42,-42,-42,-42},
    /*71*/        {99,99,45,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,41,99,99,99,99,99,99,99,99,42,43,44,99,99,99,99,99},
    /*72*/        {-16,-16,-16,-16,-16,-16,-16,-16,-16,-16,-16,-16,-16,-16,-16,-16,-16,-16,-16,-16,-16,-16,-16,-16,-16,-16,-16,-16,-16,-16,-16,-16,-16,-16,-16,-16},
    /*73*/        {-17,-17,-17,-17,-17,-17,-17,-17,-17,-17,-17,-17,-17,-17,-17,-17,-17,-17,-17,-17,-17,-17,-17,-17,-17,-17,-17,-17,-17,-17,-17,-17,-17,-17,-17,-17},
    /*74*/        {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,18,99,99,99,21,99,99,99,99,99,22,23,24,99,99,99,99,99,99,99,99},
    /*75*/        {-31,-31,-31,-31,-31,-31,-31,-31,-31,-31,-31,-31,-31,-31,-31,-31,-31,-31,-31,-31,-31,-31,-31,-31,-31,-31,-31,-31,-31,-31,-31,-31,-31,-31,-31,-31},
    /*76*/        {-22,-22,-22,-22,-22,-22,63,-22,64,-22,-22,-22,-22,-22,-22,-22,-22,-22,-22,-22,-22,-22,-22,-22,-22,-22,-22,-22,-22,-22,-22,-22,-22,-22,-22,-22},
    /*77*/        {-24,69,-24,-24,-24,67,-24,-24,-24,68,-24,-24,-24,-24,-24,-24,-24,-24,-24,-24,-24,-24,-24,-24,-24,-24,-24,-24,-24,-24,-24,-24,-24,-24,-24,-24},
    /*78*/        {-26,-26,-26,-26,-26,-26,-26,-26,-26,-26,-26,-26,-26,-26,-26,-26,-26,-26,-26,-26,-26,-26,-26,-26,-26,-26,-26,-26,-26,-26,-26,-26,-26,-26,-26,-26},
    /*79*/        {-18,-18,-18,-18,-18,-18,-18,-18,-18,-18,-18,-18,-18,-18,-18,-18,-18,-18,-18,-18,-18,-18,-18,-18,-18,-18,-18,-18,-18,-18,-18,-18,-18,-18,-18,-18}
    };
    //</editor-fold>
    private static int[][] goTo = new int[][]{//<editor-fold desc="Too Long for words. has the Go To">
            {100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116},//0
            {3,4,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},//5
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,11,99,99,99,99,99,99,99,99,99,99,99,99,99,99},//10
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,16,99,99,99,99,99,99,99,99,99,99,99,99,99},//15
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,19,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,20,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,25,26,27,28,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},//20
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},//25
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},//30
            {99,99,99,99,40,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,46,47,48,49,50,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,53,48,49,50,99,99,99},//35
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,25,99,54,28,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},//40
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},//45
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,65,66,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,70},//50
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},//55
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},//60
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},//65
            {99,99,99,99,99,99,99,99,99,99,99,75,49,50,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,76,50,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},//70
            {99,99,99,99,99,99,99,99,99,99,99,99,99,77,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,25,99,78,28,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,66,99},//75
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,70},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99},
            {99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99,99}//79
    };
    //</editor-fold>
    public static Stack<Token> parseStack = new Stack<>(); //stack used in parsing
    public Hashtable<String,Token> symbolTable = new Hashtable<>(); //the symboltable
    public static List<Token> toSemAn = new ArrayList<>();  //takes what is popped of parsestack. Used for testing
    public static List<ArrayList<String>> printlist = new ArrayList<>();    //used to print the list of quads for code gen
    public List<String> IDholder = new ArrayList<>();   //used to get the index of the IDs in the file
    public int codeGenCounter=666; //temp number to use for holding variables for code gen. These are stored in a token's tokenTempCode. Not all of them have it.
    public boolean backPatchIfFlag=false; //denotes an "if" token was seen
    private Stack<Integer> backStack = new Stack<>(); //used to store the last line number of the condition
    private int backNum = backStack.size(); //backpatch number for code gen, size() of backStack?
    /**
     * Used to print the action and goTo arrays.
     */
    public void printTables() {
        //parse table printing
        System.out.println("\nAction Table: ");
        for (int [] row:action) {
            System.out.println(Arrays.toString(row));
        }
        // makes for an even print style
        /*for (int row = 0; row < pTable.length; row++) {
            for (int col = 0; col < pTable[row].length; col++) {
                System.out.print(pTable[row][col] + "\t");
                } System.out.println();
        }*/
        //go To table printing
        System.out.println("\nGo To Table: ");
        for (int [] row:goTo) {
            System.out.println(Arrays.toString(row));
        }
    }
    /**
     * This drives the parsing process.
     * @return  True if the file is syntactically correct. False if it does not.
     */
    public boolean driver() {
        shift(new Token("0","","",0, 0, null), 0); //start
        for (int i = 0; i < Scanner.parseReady.size(); i++) {
            Token s=Scanner.parseReady.get(i);//get the token
            if (s.tokenName.matches("\\s*")){//skip empty tokens if any
                continue;
            }
            System.out.println("\nToken to parse: "+s);

            if (s.tokenName.equals("if")){ //used for back patching
                backPatchIfFlag=true;
                System.out.println("IF SPOTTED!!!!!!!! backPatchIfFlag= "+backPatchIfFlag); //test
            }

            if (!parseStack.isEmpty()) {
                //find the token column for the token value
                //You want the token value of whatever is on the top of the stack; row to go to
                int rowNum = parseStack.peek().tokenCode;
                //You want the token value of the current token
                int curNum = s.tokenCode;
                //You want the colNum where you can find curNum
                int colNum = findCol(curNum);
                // You want to find the number of [row]and[col] for the action
                int actionRow = findAction(rowNum, colNum);
                System.out.println("\nAction: " + actionRow + " rowNum: " + rowNum + " curNum: " + curNum + " colNum: " + colNum);   //test
                if (action(actionRow) == '+') {
                    shift(s, actionRow);
                    shift(new Token(String.valueOf(actionRow), "", " ", actionRow, 0, null), actionRow);
                }
                else if (action(actionRow) == '-') {
                    i--; //you want to stay on that current token for later and reduce first.
                    if (reduce(actionRow)){// if reduced properly
                        continue;//go to next iteration
                    }else return false;
                }
                else return true; //(action(actionRow) == 0)
            }
        }
        reduce(-1);//finally reduce everything to based on Rule 1 nonT: start
        if(parseStack.peek().tokenCode == 3){//if there are 3 elements to the stack, mainly 0 start and last num
            generateObjFile();
            return true;
        } else return false;
    }
    /**
     * Prints out the symbol table and its values.
     */
    public void symTabPrint (){
        System.out.println("\nSYMBOL TABLE: ");
        for (Object s: symbolTable.entrySet()) {
            System.out.println(s);
        }
    }
    /**
     * Finds the specific Integer in the 2D action array given row and column parameters.
     * @param rowToFind row of the element
     * @param colToFind column of the element
     * @return Interger taken from the value of action[row][col]
     * @throws ArrayIndexOutOfBoundsException   Due to the fact that numbers out of the length of the total columns and roms maybe passed, we catch these.
     */
    public static int findAction(int rowToFind, int colToFind) throws ArrayIndexOutOfBoundsException{
        int theAction=0;
        if (rowToFind<action.length&&colToFind<action[rowToFind].length){
            theAction=action[rowToFind+1][colToFind];
        }
        return theAction;
    }
    public static int findgoTo(int rowToFind, int colToFind) throws ArrayIndexOutOfBoundsException{
        int thegoTo=0;
        if (rowToFind<goTo.length&&colToFind<goTo[rowToFind].length){
            thegoTo=goTo[rowToFind+1][colToFind];
        }
        return thegoTo;
    }
    /**
     * Find column index of a token in action[][] array.
     * @param colToFind The symbol passed to find.
     * @return  the Integer number of the index colToFind is under.
     */
    public static int findCol(int colToFind){
        int baseColLen=0; //colomn number
        for (int i = 0; i <action[0].length ; i++) {
            if (colToFind==action[0][i]){
                baseColLen=i;
            }
        }
        //System.out.print("baseColLen: "+baseColLen+"\n");    //test
        return baseColLen;
    }
    /**
     * Find column index of a token in goTo[][] array.
     * @param colToFind The symbol passed to find.
     * @return the Integer number of the index colToFind is under.
     */
    public static int findColgoTo(int colToFind){
        int baseColLen=0; //colomn number
        for (int i = 0; i <goTo[0].length ; i++) {
            if (colToFind==goTo[0][i]){
                baseColLen=i;
            }
        }
        return baseColLen;
    }
    /**
     * Decides whether to reduce or shift
     * @param i integer value either positive (shift) or negative (reduce)
     * @return  + or - or 99(error)
     */
    public static char action(int i){
        if (i>0) return '+'; //shift
        if (i<0) return '-'; //reduce
        return 99; //error
    }
    /**
     * Shifts a token unto the parse stack.
     * @param S Token to be shifted.
     * @param shiftNum  Rruduction Rule
     */
    public void shift(Token S, int shiftNum){
        System.out.println("Shifting..."+shiftNum); //action
        parseStack.push(S);//push S
        //TEST
        System.out.print("\nParseStack: "+parseStack+"\n\n"); //print stack after shifting test
    }
    /**
     *
     * @param reduceNum this is the row number for the 2D array Rules.rules; Production Num
     */
    public boolean reduce(int reduceNum){
        //System.out.println("\nBefore ParseStack: "+parseStack+"\n"); //print stack after reducing test

        System.out.println("\nReducing..."+reduceNum); //action
        reduceNum*=-1;//convert the reduce num to positive
        //S is the current Token
        int n = parseStack.peek().tokenCode; //token value of top of current stack
        //get the value of the nonterminal stored in the grammar rule [ruleNum] in Rules.rules[ruleNum][0]; this is the col for goTo
        int nonT=Rules.rules[reduceNum][0];
        int colNum=findColgoTo(nonT);//find the column for the nonterminal nonT
        int popNum = Rules.rules[reduceNum][1]*2;//Take the number of items to pop from the nonterminal rule * 2
        int gotoRow=0;
        if((parseStack.size()-Rules.rules[reduceNum][1])<parseStack.size()){// Rules.rules[reduceNum][1] = number of rhs in production rule
            gotoRow = parseStack.get(parseStack.size()-popNum-1).tokenCode;
        }
        int goToNumTemp = findgoTo(gotoRow,colNum);//the goto rule value
        Token lhs = new Token(Rules.findStringValue(nonT),"","",nonT, 0, null);
        //check semantics
        String semanticResult=typeMatch(reduceNum);
        //based on the return do one of following
        if (semanticResult.equals("NO")) System.out.println("Semantics: DID NOT PASS");
        else if (semanticResult.equals("YES")) System.out.println("Semantics: PASSED");
        else {
            lhs.tokenValue=semanticResult; //update the nonterminal.tokenValue to be pushed to stack
        }
        symTabPrint(); //test

        //reduce till all RHS of the non-terminal is gone
        for (int i = 0; i < Rules.rules[reduceNum][1]; i++) {
            parseStack.pop(); //pop the num on top first e.g. top=> 37, $, 29 } etc...
            //System.out.println("\nparseStack top: "+parseStack.peek()+" parstack token code: "+parseStack.peek().tokenCode+" Rule: "+Rules.rules[reduceNum][i+2]); //test
            if (parseStack.peek().tokenCode==Rules.rules[reduceNum][i+2]){//if the value on top of the stack is equivalent to the val at the rhs of the rule; +2 for rhs index start at 2
                //TEST
                //System.out.println("\n\nAdding this token to toSeman: "+parseStack.peek().tokenName+" "+parseStack.peek().tokenCode+"\n toSeman currently has"+toSemAn+"\n\n"); //test
                //System.out.println("\nRemoving this from parseStack: "+parseStack.peek()); //test
                //TEST END
                toSemAn.add(parseStack.pop());//pop the Token off the stack
            }else{
                Error.RHSNotFound(parseStack.peek().tokenCode);
                return false;
            }
        }
        //get the value of the token.tokenvalue on the top of the after popped Stack; this is the row for findgoTo
        int topNum=0;
        if (!parseStack.isEmpty()){
            topNum=parseStack.peek().tokenCode;
        }
        int goToNum = findgoTo(topNum,colNum);//the goto rule value
        //System.out.println("\ntop/rowNum "+topNum+" nonT: "+nonT+" goToNum: "+goToNum+" colNum: "+colNum+" topNum: "+topNum+" gotoRow: "+gotoRow);     //test
        // push the Nonterminal.tokenValue + the goTo number
        //Token lhs = new Token(Rules.findStringValue(nonT),"nonterminal","",nonT, 0);

        int lineToPass=0;
        if (toSemAn.size()>0) lineToPass=toSemAn.get(0).tokenLine; //get the line code to pass to non T
        parseStack.push(lhs);
        parseStack.push(new Token(String.valueOf(goToNum), "", "", goToNum, lineToPass, null));

        System.out.println("\nAfter ParseStack: "+parseStack+"\n"); //print stack after reducing test
        //symTabPrint(); //test
        return true;
    }
    public void backPatching(){
        int backNum = backStack.pop(); //get the number to update op3 of JNE backpatch line from stack
        ArrayList<String> quad = printlist.get(backNum);
        System.out.println("HEY! printlist.get(backNum).get(3): "+printlist.get(backNum).toString()+"\tbacknum:"+backNum+"\tprintList size is:"+printlist.size()+"\tquad.get(3): "+quad.get(3).toString()); //test
        quad.remove(3); //remove the third operand
        quad.add(String.valueOf("#"+printlist.size())); //add the new op3 in place of what was removed
        printlist.set(backNum,quad); //replace the arraylist in the index of the backNum
        System.out.println("HEY! printlist.get(backNum).get(3): "+printlist.get(backNum).toString()+"\tbacknum:"+backNum+"\tprintList size is:"+printlist.size()+"\tquad.get(3): "+quad.get(3).toString());//test
        backPatchIfFlag=false; //set flag to back to false
    }
    /**
     * In here semantics and code generation is performed based on parsestack elements, IDholder inedexes and tokens.
     * @param rule  The grammar rule the compiler is currently on
     * @return  returns a value to update the lhs token with or if the semantics is succeful or not (Yes or No).
     */
    public String typeMatch(int rule){
            switch (rule){
                case 0:     //{0, 0, 0}
                    break;
                case 1:     //{ 100, 12, 8, 7, 105, 103, 6, 5, 102, 4, 3, 2, 1, 101}
                    quadPrint("NOP ","","","");//NOP
                    quadPrint("HLT ","","","");//HALT
                    break;
                case 2:     //{ 101(access), 1, 9(public)}
                    break;
                case 3:     //{ 101(access), 1, 10(private)}
                    break;
                case 4:     //{ 102(identifier_list), 1, 3(ID)}
                    //put ID into symbol table if it isn't already in there
                    Token IDtoken4 = parseStack.get(parseStack.size() - 2); //get the ID from parseStack
                    if (!IDholder.contains(IDtoken4)) IDholder.add(IDtoken4.tokenName); //for code gen
                    if (!symbolTable.contains(IDtoken4)) symbolTable.put(IDtoken4.tokenName,IDtoken4);
                    //make identifier_list token and add ID to tokenList of identifier_list token
                    Token idList;
                    //check if symbol table has identifier_list token
                    if (symbolTable.contains("identifier_list")){//if identifier_list is in the symbol table
                        idList=symbolTable.get("identifier_list"); //take the "identifier_list" from the symbol table
                        idList.tokenList.add(IDtoken4); //add ID to that list
                        symbolTable.replace("identifier_list", idList); //replace the old "identifier_list" token in the symboltable with the new "identifier_list" token
                    }else { //if it is not there
                        List<Token> idenlist = new ArrayList<>(); //make a List of Tokens
                        idenlist.add(IDtoken4); //add ID to that list
                        idList=new Token("identifier_list","","",102, parseStack.get(parseStack.size() - 2).tokenLine, idenlist); //create the "identifier_list" token with the list of tokens with ID in it
                        symbolTable.put(idList.tokenName,idList); //put identifier_list token into symboltable
                    }
                    break;
                case 5:     //{ 102(identifier_list), 3, 102(identifier_list), 11(,), 3(ID)}
                    //put ID into symbol table if it isn't already in there
                    Token IDtoken5 = parseStack.get(parseStack.size() - 2); //get the ID(-2) from parseStack
                    if (!IDholder.contains(IDtoken5)) IDholder.add(IDtoken5.tokenName); //for code gen
                    if (!symbolTable.contains(IDtoken5)) symbolTable.put(IDtoken5.tokenName,IDtoken5); //put ID in symboltable if it ins't there already
                    //update identifier_list (-6)
                    idList=symbolTable.get("identifier_list"); //take the "identifier_list" from the symbol table
                    idList.tokenList.add(IDtoken5); //add ID to that list
                    //System.out.println("\"identifier_list\" TOKENLIST ELEMENTS: "+idList.tokenList);//TEST
                    symbolTable.replace("identifier_list", idList); //replace the old "identifier_list" token in the symboltable with the new "identifier_list" token
                    break;
                case 6://{ 102(identifier_list), 0, 0}
                    break;
                case 7://{ 103(declarations), 6, (declarations)103, (var)12, (identifier_list)102, (:)13, (type)104, (;)14}
                    //SETS EACH OF THE ID AFTER VAR TO THE TYPE
                    //symTabPrint();//test
                    Token idlist = symbolTable.get("identifier_list");
                    Token type = symbolTable.get("type"); //get the type(-4) from symboltable
                    //set IDs taken from names from identifier_list token's tokenList in the symboltable to the type in the symboltable
                    if(idlist.tokenList!=null){
                        for (Token s:idlist.tokenList) {
                            //s.tokenType=type.tokenType; //set the type of type to every Token under the identifier list test
                            //update the type of each ID in the symbol table as well
                            if (symbolTable.contains(s)) symbolTable.get(s.tokenName).tokenType=type.tokenType;
                        }
                    }
                    symbolTable.get("identifier_list").tokenList=null; //set identifier_list.tokenList to null for next set of IDs to process if ever there are any
                    break;
                case 8://{ 103(declarations), 0, 0},
                    break;
                case 9://{ 104(type), 1, 15(char)}
                    //make a token for type and set name to type, type and value to char, code to 104 (nonterminal code for type), linenum to the linenum of the char in the stack and null of the identifier array
                    Token typeTokenChar = new Token("type","char","char",104, parseStack.get(parseStack.size() - 2).tokenLine, null);
                    //put in symbol table if not already there
                    if (symbolTable.contains("type")) symbolTable.replace("type", typeTokenChar);
                    else symbolTable.put("type",typeTokenChar);
                    //System.out.println("\n\nTYPE WAS PLACED IN SYMBOLTABLE!!!!!!!!\tTYPE IS: "+parseStack.get(parseStack.size() - 2).tokenName+"\n"+symbolTable.get("type")+"\n\n"); //test
                    break;
                case 10://{ 104(type), 1, 16(int)}
                    //make a token for type and set name to type, type and value to int, code to 104 (nonterminal code for type), linenum to the linenum of the int in the stack and null of the identifier array
                    Token typeTokenInt = new Token("type","int","int",104, parseStack.get(parseStack.size() - 2).tokenLine, null);
                    //put in symbol table if not already there
                    if (symbolTable.contains("type")) symbolTable.replace("type", typeTokenInt);
                    else symbolTable.put("type",typeTokenInt);
                    //System.out.println("\n\nTYPE WAS PLACED IN SYMBOLTABLE!!!!!!!!\tTYPE IS: "+parseStack.get(parseStack.size() - 2).tokenName+"\n"+symbolTable.get("type")+"\n\n"); //test
                    break;
                case 11://{ 105(compound_statement), 3, 7(}), 106(statement_list), 6({)}        //* Do I do anything?
                    // set and make token for compound_statement from statement_list
                    Token cmpstate = parseStack.get(parseStack.size() - 4);
                    cmpstate.tokenName="compound_statement"; //set name
                    symbolTable.put("compound_statement", cmpstate); //add to table
                    break;
                case 12://{ 106(statement_list), 1, 107(statement)}                             //* Do I do anything?
                    // set and make token for statement_list from statement
                    Token statelist = symbolTable.get("statement");
                    statelist.tokenName="statement_list"; //set name
                    symbolTable.put("statement_list", statelist); //add to table
                    return statelist.tokenValue;
                case 13://{ 106(statement_list), 3, 107(statement), 14(;), 106(statement_list)}
                    break;
                case 14://{ 107(statement), 1, 108(lefthandside)}
                    // set and make token for statement from lefthandside
                    Token state1 = symbolTable.get("lefthandside");
                    state1.tokenName="statement"; //set name
                    symbolTable.put("statement", state1); //add to table
                    return state1.tokenValue;
                case 15://{ 107(statement), 1, 105(compound_statement)}
                    // set and make token for statement from compound_statement
                    Token state2 = symbolTable.get("compound_statement");
                    state2.tokenName="statement"; //set name
                    symbolTable.put("statement", state2); //add to table
                    return state2.tokenValue;
                case 16://{ 107(statement), 4, 5( ) ), 3(ID), 4( ( ), 17(get)}
                    //intermediate code ; you get input from user
                    Token ID16 = parseStack.get(parseStack.size()-4);
                    System.out.println("PLZ!!! IDholder.contains(ID16.tokenName): "+IDholder.contains(ID16.tokenName)+"\tIDholder.contains(ID16): "+ID16.tokenName); //test
                    if (IDholder.contains(ID16.tokenName)) {
                        quadPrint("SYS ","#1","",""+IDholder.indexOf(ID16.tokenName));
                    }
                    break;
                case 17://{ 107(statement), 4, 5( ) ), 3(ID), 4( ( ), 18(put)},
                    //intermediate code ; put is printing data. Printing characters is different from numbers.
                    Token putt = parseStack.get(parseStack.size()-4); //get ID from stack
                    Token putMe = symbolTable.get(putt.tokenName); // ID from symboltable using putt
                    symTabPrint();//test
                    System.out.println("\nRAWR putt\t"+putt.toString()+"\tDOES SYMTABLE CONTAIN PUTT?"+symbolTable.contains(putt.tokenName)+"\tPutMe type: "+putMe.tokenType+"\tPutMe value: "+putMe.tokenValue); //test
                    try {
                        String value = putMe.tokenValue; //value to print
                        if (putMe.tokenType.equals("int")) {
                            System.out.println("POT TEST NUM!: "+value); //test
                            //quadPrint("SYS ","#-2",""+IDholder.indexOf(putMe.tokenName),"");
                            quadPrint("SYS ","#-1",""+IDholder.indexOf(putMe.tokenName),"");
                        }
                        if (putMe.tokenType.equals("char")){
                            int charValue = Integer.valueOf(value); //get ascii
                            System.out.println("POT TEST: "+charValue+":"+(char)charValue); //test
                            //quadPrint("SYS ","#-1",""+(char)charValue,"");
                            quadPrint("SYS ","#-2","#"+value,"");
                        }
                    } catch (NullPointerException e){
                        e.printStackTrace();
                    } catch (NumberFormatException e){
                        e. printStackTrace();
                    }
                    break;
                case 18://{ 107(statement), 5, 107(statement), 5( ) ), 110(expression), 4( ( ), 19(if)}
                    backPatching();
                    break;
                case 19: //{ 108(lefthandside), 3, 109(righthandside), 20(=), 3(ID)} //assignment
                    //If ID is of equal type as righthandside; update ID value and lefthandside
                    String IDname = parseStack.get(parseStack.size()-6).tokenName; //get the ID key to get from parsestack
                    //if the type of ID is not the same as the type of rhs = error; if same, assign value of rhs to ID
                    Token IDst = symbolTable.get(IDname); //get ID from symboltable
                    Token RHSst = symbolTable.get("righthandside"); //get righthandside from symbol table*/
                    //code gen
                    System.out.println("POTATO  " + RHSst.tokenCode +", " + RHSst.tokenTempCode);//test
                    System.out.println("POTATOES " + RHSst.tokenName);//test
                    if (RHSst.tokenCode == 3) { //if RHSst is an ID op1 is the index of the RHSst name stored in IDholder (direct addressing) and op3 is the index of the IDst name stored in IDholder
                        quadPrint("STO ",String.valueOf(IDholder.indexOf(RHSst.tokenName)),"",String.valueOf(IDholder.indexOf(IDst.tokenName)));
                    }else if (RHSst.tokenTempCode!=-1){ //if RHSst has a temorary code which was assigned by the codeGenCounter op1 is the temporary code assigned by CodeGenCounter and op3 is the index of the IDst name stored in IDholder
                        quadPrint("STO ",String.valueOf(RHSst.tokenTempCode),"",String.valueOf(IDholder.indexOf(IDst.tokenName)));
                    }else quadPrint("STO ","#"+RHSst.tokenValue,"",String.valueOf(IDholder.indexOf(IDname))); //ALL ELSE op1 immediate data (char or int thus the #) is and op3 is the index of the IDst name stored in IDholder

                    //update data in symbol information in symboltable
                    if (IDst!=null && RHSst.tokenType.equals(IDst.tokenType)){
                        symbolTable.get(IDname).tokenValue=RHSst.tokenValue; //update value of ID in symboltable by assigning rhs.value to ID.value
                        Token lhs = new Token("lefthandside",IDst.tokenType,IDst.tokenValue,108, IDst.tokenLine, null); //create lhs token
                        symbolTable.put("lefthandside",lhs); //put token in symboltable
                        System.out.println("\n\n\nLHS TYPE PLACED"+"\nID:"+IDst+"\nRHS:"+RHSst+"\n"+symbolTable.get("lefthandside"));//test
                        return lhs.tokenValue;
                        //symTabPrint();//test
                        //return true; //passes semantics
                    }else {
                        if (IDst!=null) Error.IDTypeMismatch(IDst.tokenLine, IDst.tokenName, RHSst.tokenType, IDst.tokenType); //error type mismatch
                        //return false; //fails semantics
                    }
                    break;
                case 20: //{ 109(righthandside), 1, 110(expression)}
                    // set and make token for righthandside from expression
                    Token rhs20 = symbolTable.get("expression");
                    //rhs20.tokenName="righthandside"; //set name
                    symbolTable.put("righthandside", rhs20); //add to table
                    return rhs20.tokenValue;
                case 21: //{ 110(expression), 1, 111(simple_expression)}
                    // set and make token for expression from simple_expression
                    Token exp21 = symbolTable.get("simple_expression");
                    //exp21.tokenName="expression"; //set name
                    symbolTable.put("expression", exp21); //add to table
                    return exp21.tokenValue;
                case 22: //{ 110(expression), 3, 111(*simple_expression), 114(*relop), 111(*simple_expression)}     //ASK ABOUT THIS
                    Token re1=parseStack.get(parseStack.size()-2); //the first simple_expression
                    Token re2=parseStack.get(parseStack.size()-6); //the second simple_expression
                    Token retok = symbolTable.get("relop"); //fetch operator token off the stack
                    Token termRe; //temporary token for "term" lhs to put in symbol table
                    //semantically test if term and factor are of same type
                    try{
                        Integer firstre = Integer.parseInt(re1.tokenValue.trim());
                        Integer secondre = Integer.parseInt(re2.tokenValue.trim());
                        if (!re1.tokenType.equals(re2.tokenType)) {
                            System.out.println("ERROR: Type error.");
                            System.exit(0); //terminate program
                        }
                        String reop1="",reop2=""; //op1 and op2
                        System.out.println("OMG! \tre1.tokenName: "+re1.tokenName+"\tre2.tokenName: "+re2.tokenName+"\tre1.tokenValue: "+re1.tokenValue+"\tre2.tokenValue: "+re2.tokenValue+"\tre1.tokenVarName: "+re1.tokenVarName+"\tre2.tokenVarName: "+re2.tokenVarName); //test
                        //if the value of either ad1 or ad2 is a number (imediate data) then put # sign infront for op1 and op2
                        if (!IDholder.contains(re1.tokenName)) reop1+="#"+re1.tokenValue; //if ad1 is ! an ID use value for op1
                        if (!IDholder.contains(re2.tokenName)) reop2+="#"+re2.tokenValue; //if ad1 is ! an ID use value for op1
                        //else put the index of the ID from IDholder
                        if (IDholder.contains(re1.tokenName)) reop1+=IDholder.indexOf(re1.tokenName); //if ad1 is an ID use index for op1
                        if (IDholder.contains(re2.tokenName)) reop2+=IDholder.indexOf(re2.tokenName); //if ad2 is an ID use index for op2

                        //at this point types are fine take the relop operator and choose action
                        String re=retok.tokenValue;
                        int curLine=printlist.size(); //current line in the code gen file
                        int condjump=curLine+3; //op3 for cond instruction quad
                        int jmpjmp=curLine+4; //op3 for the jump after cond instruction quad
                        switch (re){
                            case ">":
                                if (firstre>secondre) termRe = new Token("expression","int",re1.tokenValue, 110, retok.tokenLine,null); //if re1 is bigger than re2; assign value of re1
                                else termRe = new Token("expression","int",re2.tokenValue, 110, retok.tokenLine,null);  // assign value of re2
                                termRe.tokenTempCode=codeGenCounter; //assign counter to token
                                codeGenCounter++; //increment counter
                                symbolTable.put("expression",termRe);
                                //code gen
                                quadPrint("JGT ",reop1,reop2,"#"+String.valueOf(condjump));
                                quadPrint("STO ","#1","",String.valueOf(termRe.tokenTempCode));
                                quadPrint("JMP ","","","#"+String.valueOf(jmpjmp));
                                quadPrint("STO ","#0","",String.valueOf(termRe.tokenTempCode));
                                quadPrint("JNE ",String.valueOf(termRe.tokenTempCode),"#1",""); //you set op1 to temp code, op2 to #true and op3 to the number from backpatch
                                backStack.push(printlist.size()-1); //add the current code gen line to backpatch stack
                                break;
                            case ">=":
                                if (firstre>=secondre) termRe = new Token("expression","int",re1.tokenValue, 110, retok.tokenLine,null); //if re1 is bigger than re2; assign value of re1
                                else termRe = new Token("expression","int",re2.tokenValue, 110, retok.tokenLine,null);  // assign value of re2
                                termRe.tokenTempCode=codeGenCounter; //assign counter to token
                                codeGenCounter++; //increment counter
                                symbolTable.put("expression",termRe);
                                //code gen
                                quadPrint("JGE ",reop1,reop2,"#"+String.valueOf(condjump));
                                quadPrint("STO ","#1","",String.valueOf(termRe.tokenTempCode));
                                quadPrint("JMP ","","","#"+String.valueOf(jmpjmp));
                                quadPrint("STO ","#0","",String.valueOf(termRe.tokenTempCode));
                                quadPrint("JNE ",String.valueOf(termRe.tokenTempCode),"#1",""); //you set op1 to temp code, op2 to #true and leave op3 to the number from backpatch
                                backStack.push(printlist.size()-1); //add the current code gen line to backpatch stack
                                break;
                            case "==":
                                if (firstre==secondre) termRe = new Token("expression","int",re1.tokenValue, 110, retok.tokenLine,null); //if re1 is bigger than re2; assign value of re1
                                else termRe = new Token("expression","int",re2.tokenValue, 110, retok.tokenLine,null);  // assign value of re2
                                termRe.tokenTempCode=codeGenCounter; //assign counter to token
                                codeGenCounter++; //increment counter
                                symbolTable.put("expression",termRe);
                                //code gen
                                quadPrint("JEQ ",reop1,reop2,"#"+String.valueOf(condjump));
                                quadPrint("STO ","#1","",String.valueOf(termRe.tokenTempCode));
                                quadPrint("JMP ","","","#"+String.valueOf(jmpjmp));
                                quadPrint("STO ","#0","",String.valueOf(termRe.tokenTempCode));
                                quadPrint("JNE ",String.valueOf(termRe.tokenTempCode),"#1",""); //you set op1 to temp code, op2 to #true and leave op3 to the number from backpatch
                                backStack.push(printlist.size()-1); //add the current code gen line to backpatch stack
                                break;
                            case "<=":
                                if (firstre<=secondre) termRe = new Token("expression","int",re1.tokenValue, 110, retok.tokenLine,null); //if re1 is bigger than re2; assign value of re1
                                else termRe = new Token("expression","int",re2.tokenValue, 110, retok.tokenLine,null);  // assign value of re2
                                termRe.tokenTempCode=codeGenCounter; //assign counter to token
                                codeGenCounter++; //increment counter
                                symbolTable.put("expression",termRe);
                                //code gen
                                quadPrint("JLE ",reop1,reop2,"#"+String.valueOf(condjump));
                                quadPrint("STO ","#1","",String.valueOf(termRe.tokenTempCode));
                                quadPrint("JMP ","","","#"+String.valueOf(jmpjmp));
                                quadPrint("STO ","#0","",String.valueOf(termRe.tokenTempCode));
                                quadPrint("JNE ",String.valueOf(termRe.tokenTempCode),"#1",""); //you set op1 to temp code, op2 to #true and op3 to the number from backpatch
                                backStack.push(printlist.size()-1); //add the current code gen line to backpatch stack
                                break;
                            case "<":
                                if (firstre<secondre) termRe = new Token("expression","int",re1.tokenValue, 110, retok.tokenLine,null); //if re1 is bigger than re2; assign value of re1
                                else termRe = new Token("expression","int",re2.tokenValue, 110, retok.tokenLine,null);  // assign value of re2
                                termRe.tokenTempCode=codeGenCounter; //assign counter to token
                                codeGenCounter++; //increment counter
                                symbolTable.put("expression",termRe);
                                //code gen
                                quadPrint("JLT ",reop1,reop2,"#"+String.valueOf(condjump));
                                quadPrint("STO ","#1","",String.valueOf(termRe.tokenTempCode));
                                quadPrint("JMP ","","","#"+String.valueOf(jmpjmp));
                                quadPrint("STO ","#0","",String.valueOf(termRe.tokenTempCode));
                                quadPrint("JNE ",String.valueOf(termRe.tokenTempCode),"#1",""); //you set op1 to temp code, op2 to #true and op3 to the number from backpatch
                                backStack.push(printlist.size()-1); //add the current code gen line to backpatch stack
                                break;
                            case "<>": // version of !=
                                if (firstre!=secondre) termRe = new Token("expression","int",re1.tokenValue, 110, retok.tokenLine,null); //if re1 is bigger than re2; assign value of re1
                                else termRe = new Token("expression","int",re2.tokenValue, 110, retok.tokenLine,null);  // assign value of re2
                                termRe.tokenTempCode=codeGenCounter; //assign counter to token
                                codeGenCounter++; //increment counter
                                symbolTable.put("expression",termRe);
                                //code gen
                                quadPrint("JNE ",reop1,reop2,"#"+String.valueOf(condjump));
                                quadPrint("STO ","#1","",String.valueOf(termRe.tokenTempCode));
                                quadPrint("JMP ","","","#"+String.valueOf(jmpjmp));
                                quadPrint("STO ","#0","",String.valueOf(termRe.tokenTempCode));
                                quadPrint("JNE ",String.valueOf(termRe.tokenTempCode),"#1",""); //you set op1 to temp code, op2 to #true and leave op3 to the number from backpatch
                                backStack.push(printlist.size()-1); //add the current code gen line to backpatch stack
                                break;
                            default:
                                System.out.println("ERROR: Invalid relop operator parsed at line: "+retok.tokenLine); //error test
                        }
                    }
                    catch (NumberFormatException e){
                        System.out.println("Illegal Input. At line: "+retok.tokenLine); //error
                    }
                    break;
                case 23: //{ 111(simple_expression), 1, 112(term)}
                    // set and make token for simple_expression from term
                    Token simexp = symbolTable.get("term");
                    System.out.println("OM\tsimexp.tokenName: "+simexp.tokenName); //test
                    symbolTable.put("simple_expression", simexp); //add to table
                    return simexp.tokenValue;
                case 24: //{ 111(simple_expression), 3, 112(term), 115(addop), 111(simple_expression)}
                    Token ad1=symbolTable.get("simple_expression"); //term
                    Token ad2=symbolTable.get("term"); //simple_expression
                    Token addtok = symbolTable.get("addop"); //fetch operator token off the stack
                    Token termAd; //temporary token for "term" lhs to put in symbol table
                    Token IDad = parseStack.get(parseStack.size()-10); //test
                    //System.out.println("RURU LOVES DAKOTAS BUTTOM: "+ IDad.tokenName); //test
                    //semantically test if term and factor are of same type
                    try{
                        int firstad = Integer.valueOf(ad1.tokenValue.trim());
                        int secondad = Integer.valueOf(ad2.tokenValue.trim());
                        int resultad;
                        if (!ad1.tokenType.equals(ad2.tokenType)) {//if types are not the same
                            System.out.println("ERROR: Type error at line: "+addtok.tokenLine);
                            System.exit(0); //terminate
                        }
                        String addop1="",addop2=""; //op1 and op2

                        //if the value of either ad1 or ad2 is a number (imediate data) then put # sign infront for op1 and op2
                        if (!IDholder.contains(ad1.tokenName)) addop1+="#"+ad1.tokenValue; //if ad1 is ! an ID use value for op1
                        if (!IDholder.contains(ad2.tokenName)) addop2+="#"+ad2.tokenValue; //if ad1 is ! an ID use value for op1
                        //else put the index of the ID from IDholder
                        if (IDholder.contains(ad1.tokenName)) addop1+=IDholder.indexOf(ad1.tokenName); //if ad1 is an ID use index for op1
                        if (IDholder.contains(ad2.tokenName)) addop2+=IDholder.indexOf(ad2.tokenName); //if ad2 is an ID use index for op2
                        //at this point types are fine take the addop operator and choose action
                        String add=addtok.tokenValue;
                        switch (add){
                            case "+":
                                resultad = firstad+secondad;
                                termAd = new Token(Integer.toString(resultad),"int",Integer.toString(resultad), 111, addtok.tokenLine,null);
                                termAd.tokenTempCode=codeGenCounter; //assin counter to token
                                codeGenCounter++; //increment counter
                                symbolTable.put("simple_expression",termAd); //put in symboltable

                                //code gen
                                quadPrint("ADD ",addop1,addop2,String.valueOf(termAd.tokenTempCode));
                                return termAd.tokenValue;
                            case "-":
                                resultad = firstad-secondad;
                                termAd = new Token(Integer.toString(resultad),"int",Integer.toString(resultad), 111, addtok.tokenLine,null);
                                termAd.tokenTempCode=codeGenCounter; //assin counter to token
                                codeGenCounter++; //increment counter
                                symbolTable.put("simple_expression",termAd);
                                //code gen
                                quadPrint("SUB ",addop1,addop2,String.valueOf(termAd.tokenTempCode));
                                return termAd.tokenValue;
                            default:
                                //System.out.println("ERROR: Invalid addop operator parsed: "+add); //error test
                                break;
                        }
                    }catch (NumberFormatException e){
                        System.out.println("Illegal Input. At line: "+addtok.tokenLine); //error
                        e.printStackTrace();
                    }catch (NullPointerException e){
                        System.out.println("Null Operation. At line: "+addtok.tokenLine); //error
                        e.printStackTrace();
                    }
                    symTabPrint(); //test
                    break;
                case 25: //{ 112(term), 1, 113(factor)}
                    // set and make token for term from factor
                    Token term25 = symbolTable.get("factor");
                    //term25.tokenName="term"; //set name
                    symbolTable.put("term", term25); //add to table
                    symTabPrint(); //test
                    break;
                case 26: //{ 112(term), 3, 113(factor), 116(mulop), 112(term)}
                    Token mu1=symbolTable.get("term"); //term
                    Token mu2=symbolTable.get("factor"); //factor
                    Token multok = symbolTable.get("mulop"); //fetch operator token off the stack
                    Token termMu; //temporary token for "term" lhs to put in symbol table
                    System.out.println("\nmu1.tokenValue:\t"+mu1.tokenName+":"+mu1.tokenValue+"\tmu2.tokenValue:\t"+mu2.tokenName+":"+mu2.tokenValue);
                    try{
                        int firstmu = Integer.parseInt(mu1.tokenValue.trim());
                        int secondmu = Integer.parseInt(mu2.tokenValue.trim());
                        int resultmu;
                        //semantically test if term and factor are of same type
                        if (!mu1.tokenType.equals(mu2.tokenType)) {
                            System.out.println("ERROR: Type error at line: "+multok.tokenLine);
                            System.exit(0); //terminate program
                        }
                        String muop1="",muop2=""; //op1 and op2

                        //THIS HAS PROBLEM OF IDs VS NUM FOR IMEDIATE AND DIRECT DATA PASSED TO OP1 AND OP2!

                        //if the value of either ad1 or ad2 is a number (imediate data) then put # sign infront for op1 and op2
                        if (!IDholder.contains(mu1.tokenName)) muop1+="#"+mu1.tokenValue; //if ad1 is ! an ID use value for op1
                        if (!IDholder.contains(mu2.tokenName)) muop2+="#"+mu2.tokenValue; //if ad1 is ! an ID use value for op1
                        //else put the index of the ID from IDholder
                        if (IDholder.contains(mu1.tokenName)) muop1+=IDholder.indexOf(mu1.tokenName); //if ad1 is an ID use index for op1
                        if (IDholder.contains(mu2.tokenName)) muop2+=IDholder.indexOf(mu2.tokenName); //if ad2 is an ID use index for op2
                        //at this point types are fine take the mulop operator and choose action
                        String mul=multok.tokenValue;
                        switch (mul){
                            case "*":
                                resultmu = firstmu*secondmu;
                                termMu = new Token(Integer.toString(resultmu),"int",Integer.toString(resultmu), 112, multok.tokenLine,null);
                                termMu.tokenTempCode=codeGenCounter; //assin counter to token
                                codeGenCounter++; //increment counter
                                symbolTable.put("term",termMu);
                                quadPrint("MUL ",muop1,muop2,String.valueOf(termMu.tokenTempCode));     //how to get token being assigned to?
                                return termMu.tokenValue;
                            case "/":
                                if (mu2.tokenValue.equals("0")) Error.DivideByZero(mu2.tokenName);
                                resultmu = firstmu/secondmu;
                                termMu = new Token(Integer.toString(resultmu),"int",Integer.toString(resultmu), 112, multok.tokenLine,null);
                                termMu.tokenTempCode=codeGenCounter; //assin counter to token
                                codeGenCounter++; //increment counter
                                symbolTable.put("term",termMu);
                                quadPrint("DIV ",muop1,muop2,String.valueOf(termMu.tokenTempCode));
                                return termMu.tokenValue;
                            case "%":
                                resultmu = firstmu%secondmu;
                                termMu = new Token(Integer.toString(resultmu),"int",Integer.toString(resultmu), 112, multok.tokenLine,null);
                                termMu.tokenTempCode=codeGenCounter; //assin counter to token
                                codeGenCounter++; //increment counter
                                symbolTable.put("term",termMu);
                                quadPrint("MOD ",muop1,muop2,String.valueOf(termMu.tokenTempCode));
                                return termMu.tokenValue;
                            default:
                                System.out.println("ERROR: Invalid mulop operator parsed at line: "+multok.tokenLine); //error test
                        }
                    }catch (NumberFormatException e){
                        System.out.println("Illegal Input. At line: "+multok.tokenLine); //error
                        e.printStackTrace();
                        System.exit(1);
                    }
                    symTabPrint(); //test
                    break;
                case 27: //{ 113(factor), 1, 3(ID)},
                    // set and make token for factor from ID
                    Token fac27 = symbolTable.get(parseStack.get(parseStack.size() - 2).tokenName);
                    //fac27.tokenName="factor"; //set name
                    fac27.tokenVarName=parseStack.get(parseStack.size() - 2).tokenName;
                    System.out.println("WAH:\t"+parseStack.get(parseStack.size() - 2).tokenName);
                    fac27.tokenCode=3; //set code
                    symbolTable.put("factor", fac27); //add to table
                    return fac27.tokenValue;
                case 28: //{ 113(factor), 1, 21(num)},
                    // set and make token for factor from num
                    Token fac28 = parseStack.get(parseStack.size() - 2);
                    fac28.tokenValue=fac28.tokenName; //set value to that of stack
                    //fac28.tokenName="factor"; //set name
                    fac28.tokenCode=21; //set code
                    symbolTable.put("factor", fac28); //add to table
                    return fac28.tokenValue;
                case 29: //{ 113(factor), 1, 22(true)},
                    // set and make token for factor from true
                    Token fac29 = parseStack.get(parseStack.size() - 2);
                    fac29.tokenValue="true";
                    //fac29.tokenName="factor"; //set name
                    fac29.tokenCode=22; //set code
                    symbolTable.put("factor", fac29); //add to table
                    return fac29.tokenValue;
                case 30:    //{ 113(factor), 1, 23(false)}
                    // set and make token for factor from false
                    Token fac30 = parseStack.get(parseStack.size() - 2);
                    fac30.tokenValue="false";
                    //fac30.tokenName="factor"; //set name
                    fac30.tokenCode=23; //set code
                    symbolTable.put("factor", fac30); //add to table
                    return fac30.tokenValue;
                case 31:    //{ 113(factor), 3, 35(') ,36(literal) ,35(')}
                    // set and make token for factor from num and put in symbol table
                    Token fac31 = parseStack.get(parseStack.size() - 4);
                    fac31.tokenValue=String.valueOf((int)fac31.tokenName.charAt(0)); // set the value to the literal vale; must preceed name change and CONVERT CHARACTER TO ASCIII
                    System.out.println("\n\nPOTA value is: "+fac31.tokenValue+" fac31.tokenName value is: "+fac31.tokenName); //test
                    //fac31.tokenName="factor"; //set name
                    fac31.tokenCode=36; //set code to literal code: 36
                    symbolTable.put("factor", fac31); //add to table
                    symTabPrint();//test
                    return fac31.tokenValue;
                case 32:    //{ 114(relop), 1, 24(>)}
                    Token relop32 = new Token("relop","",">",114, parseStack.get(parseStack.size() - 2).tokenLine, null); //create lhs token
                    symbolTable.put("relop",relop32); //put token in symboltable
                    break;
                case 33:    //{ 114(relop), 1, 26(>=)}
                    Token relop33 = new Token("relop","",">=",114, parseStack.get(parseStack.size() - 2).tokenLine, null); //create lhs token
                    symbolTable.put("relop",relop33); //put token in symboltable
                    break;
                case 34:    //{ 114(relop), 1, 28(==)}
                    Token relop34 = new Token("relop","","==",114, parseStack.get(parseStack.size() - 2).tokenLine, null); //create lhs token
                    symbolTable.put("relop",relop34); //put token in symboltable
                    break;
                case 35:    //{ 114(relop), 1, 27(<=)}
                    Token relop35 = new Token("relop","","<=",114, parseStack.get(parseStack.size() - 2).tokenLine, null); //create lhs token
                    symbolTable.put("relop",relop35); //put token in symboltable
                    break;
                case 36:    //{ 114(relop), 1, 25(<)}
                    Token relop36 = new Token("relop","","<",114, parseStack.get(parseStack.size() - 2).tokenLine, null); //create lhs token
                    symbolTable.put("relop",relop36); //put token in symboltable
                    break;
                case 37:    //{ 114(relop), 1, 29(<>)} where <> means !=
                    Token relop37 = new Token("relop","","<>",114, parseStack.get(parseStack.size() - 2).tokenLine, null); //create lhs token
                    symbolTable.put("relop",relop37); //put token in symboltable
                    break;
                case 38:    //{ 115(addop), 1, 30(+)}
                    Token addop38 = new Token("addop","","+",115, parseStack.get(parseStack.size() - 2).tokenLine, null); //create lhs token
                    symbolTable.put("addop",addop38); //put token in symboltable
                    break;
                case 39:    //{ 115(addop), 1, 31(-)}
                    Token addop39 = new Token("addop","","-",115, parseStack.get(parseStack.size() - 2).tokenLine, null); //create lhs token
                    symbolTable.put("addop",addop39); //put token in symboltable
                    break;
                case 40:    //{ 116(mulop), 1, 32(*)}
                    Token mulop40 = new Token("mulop","","*",116, parseStack.get(parseStack.size() - 2).tokenLine, null); //create lhs token
                    symbolTable.put("mulop",mulop40); //put token in symboltable
                    break;
                case 41:    //{ 116(mulop), 1, 33(/)}
                    Token mulop41 = new Token("mulop","","/",116, parseStack.get(parseStack.size() - 2).tokenLine, null); //create lhs token
                    symbolTable.put("mulop",mulop41); //put token in symboltable
                    break;
                case 42:    //{ 116(mulop), 1, 34(%)}
                    Token mulop42 = new Token("mulop","","%",116, parseStack.get(parseStack.size() - 2).tokenLine, null); //create lhs token
                    symbolTable.put("mulop",mulop42); //put token in symboltable
                    break;
                default:
                    return "NO";
            }
        return "YES";
    }
    /**This prints the "quad" set of instruction needed by MICE to perform the program
     * A space between the Instruction and op1 is required. Commas are required between op1 and op2 and op2 and op3
     * @param op    Instruction
     * @param op1   operand 1
     * @param op2   operand 2
     * @param op3   operand 3
     */
    public void quadPrint(String op, String op1, String op2, String op3){
        ArrayList<String> quad = new ArrayList<>();
        quad.add(op); //add opcode
        quad.add(op1+",");//add operand1
        quad.add(op2+",");//add operand2
        quad.add(op3);//add operand3
        printlist.add(quad); // add this quad to print for later; line numbers we use the index
    }
    public void generateObjFile(){
        File file = new File("COSC470_cyaustria0.obj"); //turn .txt to .obj
        try {
            BufferedWriter writeThis = new BufferedWriter(new FileWriter(file));
            if (!file.exists()) { //file gets created if it is not present at the specific location
                file.createNewFile();
            }
            int seqCounter=0; //counter for the sequence number for code gen file
            System.out.println("\n\n");
            for (ArrayList<String> x: printlist) {
                System.out.println(seqCounter+" "+x.toString()); //test
                writeThis.write(seqCounter+" ");
                for (String z: x) {
                    writeThis.write(z);
                }
                seqCounter++;
                writeThis.newLine();
            }
            writeThis.flush();
            writeThis.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
