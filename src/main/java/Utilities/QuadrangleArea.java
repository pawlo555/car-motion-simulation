package Utilities;

import java.util.Objects;

public class QuadrangleArea {
    public Vector2d leftBottom;
    public Vector2d rightBottom;
    public Vector2d leftTop;
    public Vector2d rightTop;

    public QuadrangleArea(Vector2d leftBottom, Vector2d rightBottom, Vector2d leftTop, Vector2d rightTop){
        this.leftBottom = leftBottom;
        this.rightBottom = rightBottom;
        this.leftTop = leftTop;
        this.rightTop = rightTop;
    }

    public QuadrangleArea rectDecomposition(){
        //method that returns biggest rectangle that fits in QuadrangleArea, assuming that area have left side is
        //parallel to the right side. If this condition is not satisfied, method returns biggest trapeze.
        double leftLen = leftTop.distance(leftBottom);
        double rightLen = rightTop.distance(rightBottom);
        double rightDiag = rightTop.distance(leftBottom);
        double leftDiag = leftTop.distance(rightBottom);

        Vector2d rectLeftBottom;
        Vector2d rectRightBottom;
        Vector2d rectLeftTop;
        Vector2d rectRightTop;

        int vertex = 0; //0 - LB, 1 - RB, 2 - LT, 3 - RT
        //find rectangle
        if (leftLen < rightLen){
            if (leftDiag < rightDiag) {
                vertex = 2;
                rectLeftTop = leftTop;
                rectRightTop = GeometryUtils.perpendicularProjection(rightBottom, rightTop, leftTop);
                rightLen = rectRightTop.distance(rightBottom);
                if (leftLen < rightLen){
                    rectLeftBottom = leftBottom;
                    rectRightBottom = GeometryUtils.perpendicularProjection(rightBottom, rightTop, leftBottom);
                }
                else{
                    rectRightBottom = rightBottom;
                    rectLeftBottom = GeometryUtils.perpendicularProjection(leftBottom, leftTop, rightBottom);
                }

            }
            else {
                vertex = 0;
                rectLeftBottom = leftBottom;
                rectRightBottom = GeometryUtils.perpendicularProjection(rightBottom, rightTop, leftBottom);
                rightLen = rectRightBottom.distance(rightTop);
                if (leftLen < rightLen){
                    rectLeftTop = leftTop;
                    rectRightTop = GeometryUtils.perpendicularProjection(rightBottom, rightTop, leftTop);
                }
                else{
                    rectRightTop = rightTop;
                    rectLeftTop = GeometryUtils.perpendicularProjection(leftBottom, leftTop, rightTop);
                }
            }
        }
        else{
            if (leftDiag < rightDiag) {
                vertex = 1;
                rectRightBottom = rightBottom;
                rectLeftBottom = GeometryUtils.perpendicularProjection(leftBottom, leftTop, rightBottom);
                leftLen = rectLeftBottom.distance(leftTop);
                if (leftLen < rightLen){
                    rectLeftTop = leftTop;
                    rectRightTop = GeometryUtils.perpendicularProjection(rightBottom, rightTop, leftTop);
                }
                else{
                    rectRightTop = rightTop;
                    rectLeftTop = GeometryUtils.perpendicularProjection(leftBottom, leftTop, rightTop);
                }
            }
            else {
                vertex = 3;
                rectRightTop = rightTop;
                rectLeftTop = GeometryUtils.perpendicularProjection(leftBottom, leftTop, rightTop);
                leftLen = rectLeftTop.distance(leftBottom);
                if (leftLen < rightLen){
                    rectLeftBottom = leftBottom;
                    rectRightBottom = GeometryUtils.perpendicularProjection(rightBottom, rightTop, leftBottom);
                }
                else{
                    rectRightBottom = rightBottom;
                    rectLeftBottom = GeometryUtils.perpendicularProjection(leftBottom, leftTop, rightBottom);
                }
            }
        }

        return new QuadrangleArea(rectLeftBottom, rectRightBottom, rectLeftTop, rectRightTop);
    }

    public QuadrangleArea[] splitIntoLanes(int nLanes){
        Vector2d upperPoints[] = GeometryUtils.splitIntoParts(leftTop, rightTop, nLanes);
        Vector2d lowerPoints[] = GeometryUtils.splitIntoParts(leftBottom, rightBottom, nLanes);
        QuadrangleArea[] areas = new QuadrangleArea[nLanes];
        for (int i = 0; i < nLanes; i++){
            areas[i] = new QuadrangleArea(lowerPoints[i], lowerPoints[i+1], upperPoints[i], upperPoints[i+1]);
        }
        return areas;
    }

    public QuadrangleArea[] splitIntoCells(double cellLength){
        double length = GeometryUtils.distanceFromLine(leftBottom, rightBottom, leftTop);
        int nParts = (int)(length/cellLength);
        double leftLen = leftTop.distance(leftBottom);
        double rightLen = leftTop.distance(leftBottom);
        double leftPartLen = leftLen*cellLength/length;
        double rightPartLen = rightLen*cellLength/length;
        Vector2d leftPoints[] = GeometryUtils.splitIntoPartsLen(leftBottom, leftTop, leftPartLen);
        Vector2d rightPoints[] = GeometryUtils.splitIntoPartsLen(rightBottom, rightTop, rightPartLen);
        //we have to create nParts areas that fulfill criterias of cellLength, and the last one that is smaller
        QuadrangleArea[] areas = new QuadrangleArea[nParts+1];
        for (int i = 0; i < nParts+1; i++){
            areas[i] = new QuadrangleArea(leftPoints[i], rightPoints[i], leftPoints[i+1], rightPoints[i+1]);
        }
        return areas;
    }

    public Vector2d getMiddle(){
        return GeometryUtils.findLinesCrossing(leftBottom, rightTop, rightBottom, leftTop);
    }

    @Override
    public String toString() {
        return "leftBottom=" + leftBottom +
                ", rightBottom=" + rightBottom +
                ", leftTop=" + leftTop +
                ", rightTop=" + rightTop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuadrangleArea that = (QuadrangleArea) o;
        return leftBottom.equals(that.leftBottom) &&
                rightBottom.equals(that.rightBottom) &&
                leftTop.equals(that.leftTop) &&
                rightTop.equals(that.rightTop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftBottom, rightBottom, leftTop, rightTop);
    }
}
