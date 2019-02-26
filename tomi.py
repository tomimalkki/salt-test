import rpm

def __virtual__():
    return 'tomi'

def list_rpms(*args, **kwargs):
    rpms=[]
    ts = rpm.TransactionSet()
    mi = ts.dbMatch()
    for h in mi:
        rpms.append(h['name']+"-"+h['version']+"-"+h['release'])
    return rpms

