public class Data {

    private Object key;
    private Object value;

    public Data(Object key, Object value){
        this.key=key;
        this.value = value;

    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString(){
        return this.key+" "+ this.value;

    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(!(obj instanceof Data)){
            return false;
        }

        Data data=(Data)obj;
        return data.key.equals(key) && data.value.equals(value);
    }

    @Override
    public int hashCode() {
        int result=17;
        result=31*result + key.hashCode();
        return result;
    }
}
