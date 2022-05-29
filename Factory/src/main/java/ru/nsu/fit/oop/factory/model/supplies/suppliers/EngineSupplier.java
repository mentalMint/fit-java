package ru.nsu.fit.oop.factory.model.supplies.suppliers;

import ru.nsu.fit.oop.factory.model.supplies.details.Engine;
import ru.nsu.fit.oop.factory.model.supplies.details.IProduct;
import ru.nsu.fit.oop.factory.model.warehouses.IWarehouse;

public class EngineSupplier extends Supplier {
    public EngineSupplier(IWarehouse warehouse) {
        super(warehouse);
    }

    @Override
    protected IProduct createDetail() {
        Engine engine = new Engine(getDetailId());
        incrementDetailId();
        return engine;
    }
}