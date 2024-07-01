PYTHON = $(shell which python3)
PWD = $(shell pwd)

.PHONY: clean
clean:
	find $(PWD) -name __pycache__ -type d -exec rm -rf {} +

.PHONY: test
test:
	$(PYTHON) -m unittest -v
