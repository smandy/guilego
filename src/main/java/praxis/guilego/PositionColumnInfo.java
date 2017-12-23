package praxis.guilego;

public class PositionColumnInfo {

    public static final ColumnInfo[] getColumns() {
        return new ColumnInfo[]{
                new ColumnInfo("securityId", "SecID", null),
                new ColumnInfo("ric", "ric", null),
                new ColumnInfo("microStrategy", "\u00B5Strategy",
                        new MicroStrategyRenderer()),
                new ColumnInfo("localInventoryPosition",
                        "L Inv Pos", new RedBlackIntegerRenderer("localInventoryPosition", null)),
                new ColumnInfo("localTradingPosition",
                        "L Trad Pos", new RedBlackIntegerRenderer("localTradingPosition", null)),
                new ColumnInfo("localCrossPosition", "L Cross",
                        new RedBlackIntegerRenderer("localCrossPosition", null)),
                new ColumnInfo("absLocalCrossPosition", "| Cross |",
                        new RedBlackIntegerRenderer("absLocalCrossPosition", null)),
                new ColumnInfo("localPosition", "L Pos", new RedBlackIntegerRenderer("localPosition", null)),
                new ColumnInfo("foreignInventoryPosition",
                        "FInv Pos", new RedBlackIntegerRenderer("foreignInventoryPosition", null)),
                new ColumnInfo("foreignTradingPosition",
                        "FTrad Pos", new RedBlackIntegerRenderer("foreignTradingPosition", null)),
                new ColumnInfo("foreignPosition", "F Pos", new RedBlackIntegerRenderer("foreignPosition", null)),
                new ColumnInfo("error", "Error", new NonZeroIsErrorIntegerRenderer("error", null)),
                new ColumnInfo("absError", "| Error |", new NonZeroIsErrorIntegerRenderer("absError", null))
        };
    }
}
