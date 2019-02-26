# salt-test

Salt example made by Tomi Malkki which lists installed RPM packages

Tried first to install Salt on OpenSUSE 15 - which failed to authentication problems - had no time to figure it out and installed two CentOS 7 virtual machines to act as master and minion.

### Usage
Place tomi.py on salt-master to /srv/salt/_modules/tomi.py and execute:
```
salt '*' saltutil.sync_modules
```
Add permissions to run it to file /etc/salt/master.d/tomi-auth.conf
```
external_auth:
  pam:
    saltdev:
      - '@tomi'

rest_cherrypy:
  host: 0.0.0.0
  port: 8000
  disable_ssl: True
```
There is no SSL used in this example. Should be enabled in case of production.
